package de.lgohlke.analyzer;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.Squid;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.PicoContainerFactory;
import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Registry;
import de.lgohlke.io.AstProcessor;

/**
 * <p>
 * RelatedCodeMetricAggregatorTest class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class RelatedCodeMetricAggregatorTest
{
  private final File[]               dirs = new File[] { new File("src/test/resources"), new File("src/test/") };
  private final MutablePicoContainer pico = PicoContainerFactory.createContainer();
  private Registry                   registry;
  private Squid                      squid;

  /**
   * <p>
   * setup.
   * </p>
   * 
   * @throws java.lang.Exception
   *           if any.
   */
  @Before
  public void setup() throws Exception
  {
    AstProcessor processor = new AstProcessor(pico);
    processor.init();
    processor.addDirectories(dirs);
    processor.scan();

    // SourceProject prj =
    processor.decorateTreeWithMetrics(MetricsWorthToAnalyze.LIST.toArray(new MetricDef[MetricsWorthToAnalyze.LIST.size()]));

    registry = processor.getRegistry();

    squid = processor.getSquid();
  }

  /**
   * <p>
   * doAggregation.
   * </p>
   * 
   * @throws java.lang.Exception
   *           if any.
   */
  @Test
  public void doAggregation() throws Exception
  {
    // pre
    String key = "RelatedTests/TestTestClass#test()V";
    RelatedCodeMetricAggregator aggregator = new RelatedCodeMetricAggregator(pico, squid, registry.getLocationList(), registry);

    int numberOfVarDefs = (int) squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION);
    int numberOfClazzVarDefs = (int) squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION);
    @SuppressWarnings("unchecked")
    List<Integer> listVarTypeDistance = ((List<Integer>) squid.search(key).getData(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST));
    @SuppressWarnings("unchecked")
    List<Integer> listlassVarTypeDistance = ((List<Integer>) squid.search(key).getData(ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST));

    Assert.assertEquals(3, numberOfVarDefs);
    Assert.assertEquals(4, numberOfClazzVarDefs);

    Assert.assertEquals(numberOfVarDefs, listVarTypeDistance.size());
    Assert.assertEquals(numberOfClazzVarDefs, listlassVarTypeDistance.size());

    Assert.assertEquals(2D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(9D, squid.search(key).getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(1D, squid.search(key).getDouble((MetricDef) Metric.COMPLEXITY));
    Assert.assertEquals(9D, squid.search(key).getDouble((MetricDef) Metric.LINES));
    Assert.assertEquals(8D, squid.search(key).getDouble((MetricDef) Metric.LINES_OF_CODE));
    Assert.assertEquals(4D, squid.search(key).getDouble((MetricDef) Metric.STATEMENTS));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AST_RELATED_METHODS));

    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MEAN_OF_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MAX_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MAX_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE));

    // test
    aggregator.doAggregation();

    // assert
    Assert.assertEquals(8D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(5D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(4D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(15D, squid.search(key).getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(6D, squid.search(key).getDouble((MetricDef) Metric.COMPLEXITY));
    Assert.assertEquals(40D, squid.search(key).getDouble((MetricDef) Metric.LINES));
    Assert.assertEquals(38D, squid.search(key).getDouble((MetricDef) Metric.LINES_OF_CODE));
    Assert.assertEquals(14D, squid.search(key).getDouble((MetricDef) Metric.STATEMENTS));
    Assert.assertEquals(5D, squid.search(key).getDouble(ASTMetrics.AST_RELATED_METHODS));
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MEAN_OF_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(5D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MAX_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(6D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MAX_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(6D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE));
  }

  //  @Test
  //  public void doAggregation2() throws Exception
  //  {
  //    // pre
  //    String key = "de/lgohlke/analyzer/RelatedCodeMetricAggregatorTest#doAggregation()V";
  //    RelatedCodeMetricAggregator aggregator = new RelatedCodeMetricAggregator(pico, squid, registry.getLocationList(), registry);
  //
  //    SourceCode search = squid.search(key);
  //    Assert.assertEquals(6D, search.getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
  //    Assert.assertEquals(4D, search.getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
  //    Assert.assertEquals(0D, search.getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
  //    Assert.assertEquals(3D, search.getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
  //    Assert.assertEquals(0D, search.getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
  //    Assert.assertEquals(1D, search.getDouble((MetricDef) Metric.COMPLEXITY));
  //    Assert.assertEquals(42D, search.getDouble((MetricDef) Metric.LINES));
  //    Assert.assertEquals(33D, search.getDouble((MetricDef) Metric.LINES_OF_CODE));
  //    Assert.assertEquals(27D, search.getDouble((MetricDef) Metric.STATEMENTS));
  //    Assert.assertEquals(20D, search.getDouble(ASTMetrics.AST_NUMBER_OF_ASSERT_STATEMENTS));
  //
  //    System.out.println(SerializeHelper.toXML(search.getData(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE)));
  //
  //    // test
  //    aggregator.doAggregation();
  //
  //    // assert
  //    Assert.assertEquals(3D, search.getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
  //    Assert.assertEquals(4D, search.getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
  //    Assert.assertEquals(7D, search.getDouble(ASTMetrics.AGGREGATE_SUM_VARIABLE_DEFINITION));
  //    Assert.assertEquals(1D, search.getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
  //    Assert.assertEquals(3D, search.getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
  //    Assert.assertEquals(3D, search.getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
  //    Assert.assertEquals(16D, search.getDouble(ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE));
  //    Assert.assertEquals(2D, search.getDouble((MetricDef) Metric.COMPLEXITY));
  //    Assert.assertEquals(46D, search.getDouble((MetricDef) Metric.LINES));
  //    Assert.assertEquals(36D, search.getDouble((MetricDef) Metric.LINES_OF_CODE));
  //    Assert.assertEquals(28D, search.getDouble((MetricDef) Metric.STATEMENTS));
  //
  //  }
}
