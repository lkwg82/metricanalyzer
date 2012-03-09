package de.lgohlke;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.thoughtworks.qdox.model.JavaClass;

import de.lgohlke.io.bo.TestClass;

/**
 * a shareable instance for caching results
 * 
 * @author lars
 */
@Data
@RequiredArgsConstructor
public class CommonStore
{
  /*
   * QDoxScanner
   */
  //  private final Queue<File>      scannedFiles       = new ConcurrentLinkedQueue<File>();
  private final List<File>      scannedFiles       = new ArrayList<File>();

  //  private final Queue<TestClass> testClasses        = new ConcurrentLinkedQueue<TestClass>();
  private final List<TestClass> testClasses        = new ArrayList<TestClass>();
  //  private final Queue<JavaClass> javaClasses        = new ConcurrentLinkedQueue<JavaClass>();
  private final List<JavaClass> javaClasses = new ArrayList<JavaClass>();
}
