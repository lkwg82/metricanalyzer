package de.lgohlke.AST;

import lombok.Data;


/**
 * put some information into a key to describe the token location
 *
 * @author lars
 * @version $Id: $
 */
@Data
public class VisitorKey
{
  private int    startLine;
  private int    endLine;
  private String astTokenString;
  private int    astToken;
  private String identifier;
  private String filename;
}
