package biz.company.qa.pageobjects.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * (C) 2001 - 2011 company.biz AG
 */

/**
 * little Helper class for handling regex-matches in java
 * 
 * <pre>
 * public class test {
 *     private static final String bestellNr = &quot;Vielen Dank für Deine Bestellung. Eine Bestätigung mit allen Bestelldetails ist an die angegebene E-Mail-Adresse (email1102730@anonymized-biz.company.info) geschickt worden.\n&quot;
 *             + &quot;Bei Fragen zur Bestellung wende Dich bitte unter Angabe der Bestellnummer 3027732-3139078 an unser Service-Team. Bestellnummer 1110-3139078 \n&quot;;
 * 
 *     public static void main(String[] args) {
 * 
 *         String regex = \&quot;\\s((\\d+)-(\\d+))\\s&quot;;
 *         Regexer regexer = new Regexer(bestellNr, regex);
 *         regexer.findAll();
 *         System.out.println(regexer);
 *         System.out.println(regexer.getMachtList().get(0).getMatchParts()[0]);
 * 
 *     }
 * }
 * </pre>
 * 
 * gives this ouput
 * 
 * <pre>
 * try to find: \s((\d+)-(\d+))\s
 * Vielen Dank für Deine Bestellung. Eine Bestätigung mit allen Bestelldetails ist an die angegebene E-Mail-Adresse (email1102730@anonymized-biz.company.info) geschickt worden.
 * Bei Fragen zur Bestellung wende Dich bitte unter Angabe der Bestellnummer 3027732-3139078 an unser Service-Team. Bestellnummer 1110-3139078
 * 
 * Matches: 2
 *  3027732-3139078
 *     3027732-3139078
 *     3027732
 *     3139078
 * 
 *  1110-3139078
 *     1110-3139078
 *     1110
 *     3139078
 * 
 * 
 * 3027732-3139078
 * </pre>
 */
public class Regexer {

    /**
     * Container for the Match
     * 
     
     */
    public static class MyMatcher {

        private final String match;
        private final String[] matchParts;

        /**
         * @param group
         * @param groupCount
         */
        public MyMatcher(final String group, final int groupCount) {
            match = group;
            matchParts = new String[groupCount];
        }

        /**
         * @return the match
         */
        public String getMatch() {
            return match;
        }

        /**
         * @return the matchParts
         */
        public String[] getMatchParts() {
            return matchParts.clone();
        }

        /**
         * @param i
         * @param group
         */
        public void initMatch(final int i, final String group) {
            matchParts[i] = group;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer(match);
            buffer.append('\n');

            for (final String maString : matchParts) {
                buffer.append('\t');
                buffer.append(maString);
                buffer.append('\n');
            }

            return buffer.toString();
        }
    }

    private Boolean executed = false;
    private final List<MyMatcher> matchList = new ArrayList<MyMatcher>();
    private final String regex;
    private final String text;

    /**
     * @param text
     * @param regex
     */
    public Regexer(final String text, final String regex) {
        this.text = text;
        this.regex = regex;
    }

    /**
     *
     */
    public Regexer findAll() {

        if (!executed) {

            final Pattern p = Pattern.compile(getRegex());
            final Matcher m = p.matcher(getText());
            while (m.find()) {

                final MyMatcher matcher = new MyMatcher(m.group(), m.groupCount());

                for (int i = 0; i < m.groupCount(); i++) {
                    matcher.initMatch(i, m.group(i + 1));
                }

                matchList.add(matcher);
            }

            executed = true;
        }
        return this;
    }

    /**
     * if {@link Regexer#findAll()} was not executed before, it will be lazily executed
     * 
     * @return the machtList
     */
    public List<MyMatcher> getMatchList() {
        if (!executed) {
            findAll();
        }
        return matchList;
    }

    /**
     * @return the regex
     */
    public String getRegex() {
        return regex;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return returns whether regex matched the text
     */
    public boolean matched() {
        if (executed) {
            return getMatchList().size() > 0;
        } else {
            // log.warn("not yet executed");
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("try to find: ");

        buffer.append(regex);
        buffer.append('\n');
        buffer.append(text);
        buffer.append('\n');

        if (executed) {
            buffer.append("Matches: ");
            buffer.append(matchList.size());
            buffer.append('\n');
            for (final MyMatcher m : matchList) {
                buffer.append(m);
                buffer.append('\n');
            }
        } else {
            buffer.append(" * not yet executed \n");
        }

        return buffer.toString();
    }
}
