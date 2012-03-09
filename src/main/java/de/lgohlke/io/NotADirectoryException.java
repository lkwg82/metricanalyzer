package de.lgohlke.io;

import java.io.IOException;

/**
 * <p>NotADirectoryException class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class NotADirectoryException extends IOException
{

  /**
   * 
   */
  private static final long serialVersionUID = -7697019944869055213L;

  /**
   * <p>Constructor for NotADirectoryException.</p>
   */
  public NotADirectoryException()
  {
    // ok
  }

  /**
   * <p>Constructor for NotADirectoryException.</p>
   *
   * @param arg0 a {@link java.lang.String} object.
   */
  public NotADirectoryException(final String arg0)
  {
    super(arg0);
  }

  /**
   * <p>Constructor for NotADirectoryException.</p>
   *
   * @param arg0 a {@link java.lang.Throwable} object.
   */
  public NotADirectoryException(final Throwable arg0)
  {
    super(arg0);
  }

  /**
   * <p>Constructor for NotADirectoryException.</p>
   *
   * @param arg0 a {@link java.lang.String} object.
   * @param arg1 a {@link java.lang.Throwable} object.
   */
  public NotADirectoryException(final String arg0, final Throwable arg1)
  {
    super(arg0, arg1);
  }

}
