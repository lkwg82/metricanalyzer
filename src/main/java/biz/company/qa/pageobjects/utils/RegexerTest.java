package biz.company.qa.pageobjects.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * (C) 2001 - 2011 company.biz AG
 */

public class RegexerTest {

  @Test
  public void testRegexSimple() {
    final String text = "aaaa";
    final String regex = "(a)";

    final Regexer regexer = new Regexer(text, regex);
    regexer.findAll();

    Assert.assertTrue(regexer.matched());
    Assert.assertEquals(4, regexer.getMatchList().size());
  }

  @Test
  public void testRegexMultiline() {
    final String text = "a \n sd \n 12-123 \n 123-123";
    final String regex = "(\\d+-\\d+)";

    final Regexer regexer = new Regexer(text, regex);
    regexer.findAll();

    Assert.assertTrue(regexer.matched());
    Assert.assertEquals(2, regexer.getMatchList().size());
  }

  @Test
  public void testRegexMultilineMultiCapture() {
    final String number = "123";
    final String text = "a \n sd \n 12-" + number + " \n 123-123";
    final String regex = "((\\d+)-(\\d+))";

    final Regexer regexer = new Regexer(text, regex);
    regexer.findAll();

    Assert.assertTrue(regexer.matched());
    Assert.assertEquals(2, regexer.getMatchList().size());
    Assert.assertEquals(3, regexer.getMatchList().get(0).getMatchParts().length);
    //        Assert.assertTrue(regexer.getMatchList().get(0).getMatchParts()[1].equals(number));
  }
}