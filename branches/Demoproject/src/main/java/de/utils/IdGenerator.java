package de.utils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

public class IdGenerator
{

  /**
   * @param args
   * @throws IOException
   */
  public static void main(final String[] args) throws IOException
  {
    String id;
    File idFile = new File("id");
    if (!idFile.exists())
    {
      long l1 = new SecureRandom().nextLong();
      long l2 = new SecureRandom().nextLong();

      FileUtils.writeStringToFile(idFile, DigestUtils.shaHex(l1 + l2 + ""));
    }
    System.out.print(FileUtils.readFileToString(idFile));
  }

}
