package biz.company.qa.reporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This helper class contains all tests. It's also a singleton to be sure that only one of this object does exist in a
 * test suite run.
 * 
 
 
 */
public class Tests {
    private static final Logger log = LoggerFactory.getLogger(Tests.class);
    private static final Tests INSTANCE = new Tests();
    private final Set<Test> tests = Collections.synchronizedSet(new HashSet<Test>());

    public static Tests getInstance() {
        return INSTANCE;
    }

    private Tests() {
        log.info("Creating test data handler for reporting");
    }

    public boolean add(Test e) {
        return tests.add(e);
    }

    /**
     * @deprecated use {@see #getTests()#clear()}
     */
    @Deprecated
    public void clear() {
        tests.clear();
    }

    public Test get(int index) {
        List<Test> list = new ArrayList<Test>(tests);
        return list.get(index);
    }

    /**
     * @deprecated use {@see #getTests()#isEmpty()}
     */
    @Deprecated
    public boolean isEmpty() {
        return tests.isEmpty();
    }

    /**
     * @deprecated use {@see #getTests()}
     */
    @Deprecated
    public Iterator<Test> iterator() {
        return tests.iterator();
    }

    public Test remove(int index) {
        List<Test> list = new ArrayList<Test>(tests);
        return list.remove(index);
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        tests.clear();
        tests.addAll(new HashSet<Test>(tests));
    }
}
