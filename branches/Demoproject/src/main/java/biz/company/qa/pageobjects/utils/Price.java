/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;

/**
 * This helps to convert text into real doubles to calculate with prices
 * 
 
 */
public class Price {
  private final static Logger log = LoggerFactory.getLogger(Price.class);

  private final double value;
  private String isoCode;
  private String symbol;
  private final String text;

  private final static Map<String, String> SYMBOL = new HashMap<String, String>() {

    private static final long serialVersionUID = -7372463004207679862L;

    {
      put("EUR", "€");
      put("GBP", "£");
      put("NOK", "kr");
      put("DKK", "kr");
      put("PLN", "zł");
      put("SEK", "kr");
    }
  };

  private final static Map<String, String> ISOCODE = new HashMap<String, String>() {

    private static final long serialVersionUID = -233841507983276811L;

    {
      put("€", "EUR");
      put("£", "GBP");
      put("kr", "NOK");
      put("zł", "PLN");
    }
  };

  private Price(final double value, final String isoCode) {
    this(value, isoCode, SYMBOL.get(isoCode), "");
  }

  private Price(final double value, final String isoCode, final String text) {
    this(value, isoCode, SYMBOL.get(isoCode), text);
  }

  private Price(final double value, final String isoCode, final String symbol, final String text) {
    this.value = value;
    this.isoCode = isoCode;
    this.symbol = symbol;
    this.text = text;
  }

  public static Price fromApiPrice(final biz.company.api.Price price,
      final biz.company.api.CurrencyDTO currency) {
    String formatString = currency.getPattern();
    String currencySymbol = currency.getSymbol();
    String priceString = price.getVatIncluded().toPlainString();
    return Price.parse(formatString.replaceAll("\\$", priceString).replaceAll("%", currencySymbol));
  }

  public static Price parse(final String text) {
    final String currencyRegex = "([a-zA-Z]{3}|€|£|zł|kr)";
    final String valueRegex = "([0-9.,]+)";

    // check for currency or iso code
    Regexer regexer = new Regexer(text.trim(), currencyRegex);
    regexer.findAll();
    if (!regexer.matched()) {
      throw new SkipException("Could not parse price. No currency symbol or iso code found: " + text);
    }

    // only one currency symbol or iso code
    if (regexer.getMatchList().size() != 1) {
      throw new SkipException("Could not parse price. Too many iso codes or currency signs: " + text);
    }

    String currency = regexer.getMatchList().get(0).getMatchParts()[0];

    // check for numbers
    regexer = new Regexer(text.trim(), valueRegex);
    regexer.findAll();
    if (!regexer.matched()) {
      throw new SkipException("Could not parse price. No numbers found: " + text);
    }

    String value = regexer.getMatchList().get(0).getMatchParts()[0];
    if (text.contains("-")) {
      value = "-" + value;
    }
    return new Price(parseValue(value), parseCurrency(currency), text);
  }

  private static double parseValue(final String value) {
    if (value.contains(".") && value.contains(",")) {
      throw new SkipException("Sorry, can not parse such a high number ;)");
    }

    if (value.contains(".")) {
      return Double.valueOf(value);
    } else if (value.contains(",")) {
      return Double.valueOf(value.replace(",", "."));
    } else {
      return Double.valueOf(value);
    }
  }

  private static String parseCurrency(final String currency) {
    // check if the given currency is an ISO 4217 code
    if (currency.length() == 3) {
      if (SYMBOL.containsKey(currency)) {
        return currency;
      } else {
        throw new SkipException("Currency iso code '" + currency + "' is unknown.");
      }
    }

    // check if the given currency is a symbol
    if (ISOCODE.containsKey(currency)) {
      // now it gets kind of ugly, b/c the value is not unique
      return ISOCODE.get(currency);
    } else {
      throw new SkipException("Currency symbol '" + currency + "' is unkown.");
    }
  }

  /**
   * Returns the price together with the currency symbol
   */
  @Override
  public String toString() {
    return value + " " + symbol;
  }

  public double getValue() {
    return value;
  }

  public void getIsoCode(final String isoCode) {
    this.isoCode = isoCode;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public void setSymbol(final String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getOriginalText() {
    return text;
  }

  /* *************************************
   * ARITHMETIC OPERATIONS: add, multiply
   */

  public Price add(final Price summand) {
    // check if same currency
    Assert.assertEquals(this.getIsoCode(), summand.getIsoCode());

    // convert to BigDecimal to avoid rounding errors
    BigDecimal summand1 = BigDecimal.valueOf(getValue());
    BigDecimal summand2 = BigDecimal.valueOf(summand.getValue());
    BigDecimal sum = summand1.add(summand2);

    return new Price(sum.doubleValue(), this.getIsoCode());
  }

  public Price subtract(final Price subtrahend) {
    // check if same currency
    Assert.assertEquals(this.getIsoCode(), subtrahend.getIsoCode());

    // convert to BigDecimal to avoid rounding errors
    BigDecimal minuend = BigDecimal.valueOf(getValue());
    BigDecimal subtrahend2 = BigDecimal.valueOf(subtrahend.getValue());
    BigDecimal difference = minuend.subtract(subtrahend2);

    return new Price(difference.doubleValue(), this.getIsoCode());
  }

  public Price multiply(final double multiplicand) {
    // convert to BigDecimal to avoid rounding errors
    BigDecimal factor1 = BigDecimal.valueOf(getValue());
    BigDecimal factor2 = BigDecimal.valueOf(multiplicand);
    BigDecimal product = factor1.multiply(factor2);
    // set scale to 2 digits after decimal point
    product = product.setScale(2, BigDecimal.ROUND_HALF_UP);

    return new Price(product.doubleValue(), this.getIsoCode());
  }

  public Price divide(final double dividend) {
    // convert to BigDecimal to avoid rounding errors
    BigDecimal factor1 = BigDecimal.valueOf(getValue());
    BigDecimal factor2 = BigDecimal.valueOf(1 / dividend);
    BigDecimal product = factor1.multiply(factor2);
    // set scale to 2 digits after decimal point
    product = product.setScale(2, BigDecimal.ROUND_HALF_UP);

    return new Price(product.doubleValue(), this.getIsoCode());
  }

  /* *************************
   * EQUALS METHOD FOR TESTNG
   */

  /**
   * This is the equals method that is used by TestNG
   */
  @Override
  public boolean equals(final Object o) {
    if (o instanceof Price) {
      Price actual = (Price) o;
      if (actual.getValue() != getValue()) {
        log.debug("Value of prices are not the same. " + getErrorMessage(actual, this));
        return false;
      }
      if (!actual.getSymbol().equals(getSymbol())) {
        log.debug("Symbol of prices are not the same. " + getErrorMessage(actual, this));
        return false;
      }
      if (!actual.getIsoCode().equals(this.getIsoCode())) {
        log.debug("ISO code of prices are not the same. " + getErrorMessage(actual, this));
        return false;
      }
      return true;
    } else {
      log.debug("Object is not an instance of class Price.");
      return false;
    }
  }

  /**
   * This is important. See https://spaces.companomat.net/pages/viewpage.action?pageId=44029480
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((isoCode == null) ? 0 : isoCode.hashCode());
    result = (prime * result) + ((symbol == null) ? 0 : symbol.hashCode());
    long temp;
    temp = Double.doubleToLongBits(value);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    return result;
  }

  private static String getErrorMessage(final Price actual, final Price expected) {
    return "Expected: " + expected.getValue() + " " + expected.getIsoCode() + " (" + expected.getSymbol()
        + "), but saw: " + actual.getValue() + " " + actual.getIsoCode() + " (" + actual.getSymbol() + ").";
  }

}