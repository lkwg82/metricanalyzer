package de.lgohlke.qdox;

import com.thoughtworks.qdox.model.JavaSource;

public interface IQDoxScanHandler
{

  void scanSource(JavaSource source);

}
