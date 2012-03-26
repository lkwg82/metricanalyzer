package de.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampGenerator
{

  /**
   * @param args
   * @throws IOException
   */
  public static void main(final String[] args) throws IOException
  {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    System.out.print(format.format(new Date()));
  }

}
