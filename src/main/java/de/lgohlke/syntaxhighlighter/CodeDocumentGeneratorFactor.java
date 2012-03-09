package de.lgohlke.syntaxhighlighter;

import java.io.File;

import org.sonar.squid.api.SourceProject;

import de.lgohlke.io.JavaTestClassFinder;

public final class CodeDocumentGeneratorFactor
{

  public static CodeDocumentGenerator createDefaultInstance(final JavaTestClassFinder finder, final SourceProject project, final File directoryToWriteTo)
  {
    File resourcePath = new File("src/main/resources/syntaxhighlighter_3.0.83");

    CodeDocumentGenerator documentator = new CodeDocumentGenerator(finder, project);
    documentator.setResourcePath(resourcePath);
    documentator.setTargetDirectory(directoryToWriteTo);

    return documentator;
  }

  private CodeDocumentGeneratorFactor()
  {
    // ok
  }
}
