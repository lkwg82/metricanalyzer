package de.lgohlke.AST;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RegistryTest
{
  //
  //  @Test
  //  public void type() throws Exception
  //  {
  //    // TODO auto-generated by JUnit Helper.
  //    assertThat(Registry.class, notNullValue());
  //  }

  //  @Test
  //  public void instantiation() throws Exception
  //  {
  //    // TODO auto-generated by JUnit Helper.
  //    Registry target = new Registry();
  //    assertThat(target, notNullValue());
  //  }

  //  @Test
  //  public void printStats_A$() throws Exception
  //  {
  //    // TODO auto-generated by JUnit Helper.
  //    Registry target = new Registry();
  //    // given
  //    // e.g. : given(mocked.called()).willReturn(1);
  //    // when
  //    String actual = target.printStats();
  //    // then
  //    // e.g. : verify(mocked).called();
  //    String expected = null;
  //    assertThat(actual, is(equalTo(expected)));
  //  }
  //
  //  @Test
  //  public void getImports_A$String() throws Exception
  //  {
  //    // TODO auto-generated by JUnit Helper.
  //    Registry target = new Registry();
  //    // given
  //    String path = null;
  //    // e.g. : given(mocked.called()).willReturn(1);
  //    // when
  //    String[] actual = target.getImports(path);
  //    // then
  //    // e.g. : verify(mocked).called();
  //    String[] expected = null;
  //    assertThat(actual, is(equalTo(expected)));
  //  }

  @Test
  public void setImports_A$String$StringArray() throws Exception
  {
    // TODO auto-generated by JUnit Helper.
    Registry target = new Registry();
    // given
    String file = null;
    String[] imports = new String[] {};
    // e.g. : given(mocked.called()).willReturn(1);
    // when
    target.setImports(file, imports);
    // then
    // e.g. : verify(mocked).called();
  }

  @Test
  public void getCanonicalClassnameOfType_1() throws Exception
  {
    Registry target = new Registry();

    // given
    String key = "a";
    target.setImports(key, new String[] { "java.io.File" });
    String typeName = "File";

    // when
    String actual = target.getCanonicalClassnameOfType(key, typeName);

    // then
    String expected = "java.io.File";
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void getCanonicalClassnameOfType_2() throws Exception
  {
    Registry target = new Registry();

    // given
    String key = "a";
    target.setImports(key, new String[] { "java.io.File" });
    target.getPackageName().put(key, "de.lgohlke.analyzer");
    String typeName = "de.lgohlke.analyzer.RelatedCodeMetricAggregator";

    // when
    String actual = target.getCanonicalClassnameOfType(key, typeName);

    // then
    String expected = "de.lgohlke.analyzer.RelatedCodeMetricAggregator";
    assertThat(actual, is(equalTo(expected)));
  }

  // @Test
  // public void addMetricPerLine_A$Object$MetricDef$Double() throws Exception {
  // // TODO auto-generated by JUnit Helper.
  // Registry target = new Registry();
  // // given
  // Object key = null;
  // MetricDef metric = mock(MetricDef.class);
  // Double value = null;
  // // e.g. : given(mocked.called()).willReturn(1);
  // // when
  // target.addMetricPerLine(key, metric, value);
  // // then
  // // e.g. : verify(mocked).called();
  // }

  // @Test
  // public void getMetricPerLineMap_A$() throws Exception {
  // // TODO auto-generated by JUnit Helper.
  // Registry target = new Registry();
  // // given
  // // e.g. : given(mocked.called()).willReturn(1);
  // // when
  // Map actual = target.getMetricPerLineMap();
  // // then
  // // e.g. : verify(mocked).called();
  // Map expected = null;
  // assertThat(actual, is(equalTo(expected)));
  // }

  // @Test
  // public void addDescription_A$Object$MetricDef$String() throws Exception {
  // // TODO auto-generated by JUnit Helper.
  // Registry target = new Registry();
  // // given
  // Object key = null;
  // MetricDef metric = mock(MetricDef.class);
  // String describe = null;
  // // e.g. : given(mocked.called()).willReturn(1);
  // // when
  // target.addDescription(key, metric, describe);
  // // then
  // // e.g. : verify(mocked).called();
  // }
  //
  // @Test
  // public void getDescription_A$() throws Exception {
  // // TODO auto-generated by JUnit Helper.
  // Registry target = new Registry();
  // // given
  // // e.g. : given(mocked.called()).willReturn(1);
  // // when
  // Map actual = target.getDescription();
  // // then
  // // e.g. : verify(mocked).called();
  // Map expected = null;
  // assertThat(actual, is(equalTo(expected)));
  // }

}