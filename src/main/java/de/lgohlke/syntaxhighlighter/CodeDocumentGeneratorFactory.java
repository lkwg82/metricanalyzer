package de.lgohlke.syntaxhighlighter;

import java.io.File;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceProject;

/**
 * <p>CodeDocumentGeneratorFactory class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public final class CodeDocumentGeneratorFactory
{

  /**
   * <p>createDefaultInstance.</p>
   *
   * @param finder a {@link de.lgohlke.io.JavaSourceScanner} object.
   * @param project a {@link org.sonar.squid.api.SourceProject} object.
   * @param directoryToWriteTo a {@link java.io.File} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.CodeDocumentGenerator} object.
   */
  public static CodeDocumentGenerator createDefaultInstance(final MutablePicoContainer pico, final SourceProject project, final File directoryToWriteTo, final Squid squid)
  {
    File resourcePath = new File("src/main/resources/syntaxhighlighter_3.0.83");

    CodeDocumentGenerator documentator = new CodeDocumentGenerator(pico, squid);
    documentator.setResourcePath(resourcePath);
    documentator.setTargetDirectory(directoryToWriteTo);

    return documentator;
  }

  private CodeDocumentGeneratorFactory()
  {
    // ok
  }
}
