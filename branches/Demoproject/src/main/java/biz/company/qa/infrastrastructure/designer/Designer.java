package biz.company.qa.infrastrastructure.designer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.pageobjects.utils.ICondition;

/**
 * This class allows basic tester of the designer. in order to use it. After creating an instance of this class, call
 * waitForDesignerReady and waitForDesignerFullyLoaded. This ensures that the designer is ready and has loaded all its
 * resources successfully.
 **/
public abstract class Designer {
    private final Selenium seleniumInstance;
    private final String flashObjectId;
    public final static String JSCallFlashMethod = "Selenium.prototype.callFlashMethod";
    protected final static Logger log = LoggerFactory.getLogger(Designer.class);

    /**
     * create a new interface to the designer
     * 
     * @param selenium the selenium object to use
     * @param flashObjectId the id of the designer object within the page you are you are using
     */
    public Designer(final Selenium selenium, final String flashObjectId) {
        seleniumInstance = selenium;
        this.flashObjectId = flashObjectId;
    }

    public String call(final String functionName, final String... args) {
        String result = seleniumInstance.getEval(jsForFunction(flashObjectId, functionName, args));

        // only show results for getXY()-methods
        if (functionName.toLowerCase().startsWith("get")) {
            log.info("Call was:  function " + functionName + " with arguments " + Arrays.deepToString(args));
        } else {
            log.info("Call result was: " + result + " for function " + functionName + " with arguments "
                    + Arrays.deepToString(args));
        }
        return result;
    }

    protected String jsForFunction(final String objectId, final String functionName, final String... args) {
        StringBuffer functionArgs = new StringBuffer();
        functionArgs.append(objectId + "','" + functionName + "'");
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                functionArgs.append(",'" + args[i] + "'");
            }
        }
        return JSCallFlashMethod + "('" + functionArgs.toString() + ")";
    }

    public String getVariable(final String varName) {
        return call("GetVariable", varName);
    }

    public void gotoFrame(final int frameNumber) {
        call("GotoFrame", Integer.toString(frameNumber));
    }

    public boolean isPlaying() {
        return "true".equals(call("IsPlaying"));
    }

    public void loadMovie(final int layerNumber, final String url) {
        call("LoadMovie", Integer.toString(layerNumber), url);
    }

    public void pan(final int x, final int y, final int mode) {
        call("Pan", Integer.toString(x), Integer.toString(y), Integer.toString(mode));
    }

    public int percentLoaded() {
        return Integer.valueOf(call("PercentLoaded"));
    }

    public void play() {
        call("Play");
    }

    public void rewind() {
        call("Rewind");
    }

    public void setVariable(final String varName, final String varValue) {
        call("SetVariable", varName, varValue);
    }

    public void setZoomRect(final int left, final int top, final int right, final int bottom) {
        call("SetZoomRect", Integer.toString(left), Integer.toString(top), Integer.toString(right),
                Integer.toString(bottom));
    }

    public void stopPlay() {
        call("StopPlay");
    }

    public int totalFrames() {
        return Integer.valueOf(call("TotalFrames"));
    }

    public void zoom(final int percent) {
        call("Zoom", Integer.toString(percent));
    }

    public void tCallFrame(final String target, final int frameNumber) {
        call("TCallFrame", target, Integer.toString(frameNumber));
    }

    public void tCallLabel(final String target, final String label) {
        call("TCallLabel", target, label);
    }

    public int tCurrentFrame(final String target) {
        return Integer.valueOf(call("TCurrentFrame", target));
    }

    public String tCurrentLabel(final String target) {
        return call("TCurrentLabel", target);
    }

    public String tGetProperty(final String target, final String property) {
        return call("TGetProperty", target, property);
    }

    public int tGetPropertyAsNumber(final String target, final String property) {
        return Integer.valueOf(call("TGetPropertyAsNumber", target, property));
    }

    public void tGotoFrame(final String target, final int frameNumber) {
        call("TGotoFrame", target, Integer.toString(frameNumber));
    }

    public void tGotoLabel(final String target, final String label) {
        call("TGotoLabel", target, label);
    }

    public void tPlay(final String target) {
        call("TPlay", target);
    }

    public void tSetProperty(final String target, final String property, final String value) {
        call("TSetProperty", target, property, value);
    }

    public void tStopPlay(final String target) {
        call("TStopPlay", target);
    }

    public void onProgress(final int percent) {
        call("OnProgress", Integer.toString(percent));
    }

    public void onReadyStateChange(final int state) {
        call("OnReadyStateChange", Integer.toString(state));
    }

    public String fsCommand(final String command, final String... args) {

        List<String> newArgs2 = new ArrayList<String>();
        newArgs2.add(command);
        newArgs2.addAll(Arrays.asList(args));

        return call("FSCommand", newArgs2.toArray(new String[newArgs2.size()]));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().//
                append(flashObjectId).//
                append(seleniumInstance).//
                toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Designer other = (Designer) obj;
        if (flashObjectId == null) {
            if (other.flashObjectId != null) {
                return false;
            }
        } else if (!flashObjectId.equals(other.flashObjectId)) {
            return false;
        }
        if (seleniumInstance == null) {
            if (other.seleniumInstance != null) {
                return false;
            }
        } else if (!seleniumInstance.equals(other.seleniumInstance)) {
            return false;
        }
        return true;
    }

    // designer specific functions which are implemented specifically for
    // confo 6 and CNG
    public abstract boolean isUIStarted();

    public abstract int getProductTypeID();

    public abstract int getProductTypeColorID();

    public abstract int getProductTypeSizeID();

    public abstract int getArticleQuantity();

    public abstract void setArticleQuantity(int quantity);

    public abstract void setDefaultProductTypeSize();

    public abstract void sendArticleToServer();

    public abstract void createProduct();

    /**
     * @return
     */
    public DesignerWaitHelper waitFor() {
        return waitFor(300000);
    }

    /**
     * @param i
     * @return
     */
    public DesignerWaitHelper waitFor(final int timeout) {
        return new DesignerWaitHelper(timeout, this);
    }

    /**
     * make a asynchronous call and wait for the result
     * 
     * @param functionName
     * @param params
     * @return
     */
    protected String callAsync(final String functionName, final String... params) {
        final StringBuffer buffer = new StringBuffer();

        ICondition condition = new ICondition() {

            @Override
            public String getErrorMessage() {
                return "async call of " + functionName + " timed out";
            }

            @Override
            public boolean check() {
                String result = call(functionName, params);
                log.info("async waiter: " + result);

                // is undefined
                boolean novalue = (result.length() == 7 && result.equals("novalue")) ? true : false;
                boolean check = (novalue || (result.length() == 11 && result.startsWith("@"))) ? false : true;

                if (check) {
                    buffer.append(result);
                }
                return check;

            }
        };

        waitFor().condition(condition);

        return buffer.toString();
    }
}
