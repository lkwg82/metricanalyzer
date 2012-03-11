package de.lgohlke.io;

/**
 * <p>AstProcessorException class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class AstProcessorException extends Exception
{

  /**
   * 
   */
  private static final long serialVersionUID = -1231719328219943173L;

  /**
   * <p>Constructor for AstProcessorException.</p>
   */
  public AstProcessorException()
  {
    // ok
  }

  /**
   * <p>Constructor for AstProcessorException.</p>
   *
   * @param arg0 a {@link java.lang.String} object.
   */
  public AstProcessorException(final String arg0)
  {
    super(arg0);
  }

  /**
   * <p>Constructor for AstProcessorException.</p>
   *
   * @param arg0 a {@link java.lang.Throwable} object.
   */
  public AstProcessorException(final Throwable arg0)
  {
    super(arg0);
  }

  /**
   * <p>Constructor for AstProcessorException.</p>
   *
   * @param arg0 a {@link java.lang.String} object.
   * @param arg1 a {@link java.lang.Throwable} object.
   */
  public AstProcessorException(final String arg0, final Throwable arg1)
  {
    super(arg0, arg1);
  }

}
