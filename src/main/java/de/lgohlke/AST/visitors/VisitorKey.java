package de.lgohlke.AST.visitors;

import com.google.gson.Gson;

/**
 * put some information into a key to describe the token location
 * 
 * @author lars
 */
public class VisitorKey
{
  private String filename;
  private int    startLine;
  private int    endLine;
  private String astTokenString;
  private int    astToken;
  private String identifier;

  public void setFilename(final String filename)
  {
    this.filename = filename;
  }

  public String getFilename()
  {
    return filename;
  }

  public void setStartLine(final int startLine)
  {
    this.startLine = startLine;
  }

  public int getStartLine()
  {
    return startLine;
  }

  public void setEndLine(final int endLine)
  {
    this.endLine = endLine;
  }

  public int getEndLine()
  {
    return endLine;
  }

  public void setAstTokenString(final String astTokenString)
  {
    this.astTokenString = astTokenString;
  }

  public String getAstTokenString()
  {
    return astTokenString;
  }

  @Override
  public String toString()
  {
    return new Gson().toJson(this);
  }

  public void setAstToken(final int astToken)
  {
    this.astToken = astToken;
  }

  public int getAstToken()
  {
    return astToken;
  }

  public void setIdentifier(final String identifier)
  {
    this.identifier = identifier;
  }

  public String getIdentifier()
  {
    return identifier;
  }
}
