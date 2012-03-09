package de.lgohlke.AST.calculators;

/**
 * <p>ICalculator interface.</p>
 *
 * @author lars
 * @version $Id: $
 */
public interface ICalculator
{
  /**
   * calculate
   *
   * @return a {@link java.lang.Double} object.
   */
  Double calculate();

  /**
   * describe some background on this calculation
   *
   * @return a {@link java.lang.String} object.
   */
  String describe();
}
