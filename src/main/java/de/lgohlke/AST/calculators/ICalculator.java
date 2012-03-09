package de.lgohlke.AST.calculators;

public interface ICalculator
{
  /**
   * calculate
   * 
   * @return
   */
  Double calculate();

  /**
   * describe some background on this calculation
   * 
   * @return
   */
  String describe();
}
