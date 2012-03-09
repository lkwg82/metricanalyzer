package de.lgohlke.analyzer;

import java.io.File;

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

    Assert.assertEquals(2D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(1D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(1D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(1D, squid.search(key).getDouble((MetricDef) Metric.COMPLEXITY));
    Assert.assertEquals(8D, squid.search(key).getDouble((MetricDef) Metric.LINES));
    Assert.assertEquals(7D, squid.search(key).getDouble((MetricDef) Metric.LINES_OF_CODE));
    Assert.assertEquals(3D, squid.search(key).getDouble((MetricDef) Metric.STATEMENTS));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AST_RELATED_METHODS));

    // test
    aggregator.doAggregation();

    // assert
    Assert.assertEquals(7D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(2D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(9D, squid.search(key).getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(6D, squid.search(key).getDouble((MetricDef) Metric.COMPLEXITY));
    Assert.assertEquals(39D, squid.search(key).getDouble((MetricDef) Metric.LINES));
    Assert.assertEquals(37D, squid.search(key).getDouble((MetricDef) Metric.LINES_OF_CODE));
    Assert.assertEquals(13D, squid.search(key).getDouble((MetricDef) Metric.STATEMENTS));
    Assert.assertEquals(5D, squid.search(key).getDouble(ASTMetrics.AST_RELATED_METHODS));
  }

  @Test
  public void doAggregation2() throws Exception
  {
    // pre
    String key = "de/lgohlke/analyzer/RelatedCodeMetricAggregatorTest#doAggregation()V";
    RelatedCodeMetricAggregator aggregator = new RelatedCodeMetricAggregator(pico, squid, registry.getLocationList(), registry);

    Assert.assertEquals(2D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(4D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(0D, squid.search(key).getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(1D, squid.search(key).getDouble((MetricDef) Metric.COMPLEXITY));
    Assert.assertEquals(31D, squid.search(key).getDouble((MetricDef) Metric.LINES));
    Assert.assertEquals(25D, squid.search(key).getDouble((MetricDef) Metric.LINES_OF_CODE));
    Assert.assertEquals(21D, squid.search(key).getDouble((MetricDef) Metric.STATEMENTS));
    Assert.assertEquals(18D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_ASSERT_STATEMENTS));

    // test
    aggregator.doAggregation();

    // assert
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(4D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION));
    Assert.assertEquals(7D, squid.search(key).getDouble(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION));
    Assert.assertEquals(1D, squid.search(key).getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE));
    Assert.assertEquals(3D, squid.search(key).getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(16D, squid.search(key).getDouble(ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE));
    Assert.assertEquals(2D, squid.search(key).getDouble((MetricDef) Metric.COMPLEXITY));
    Assert.assertEquals(46D, squid.search(key).getDouble((MetricDef) Metric.LINES));
    Assert.assertEquals(36D, squid.search(key).getDouble((MetricDef) Metric.LINES_OF_CODE));
    Assert.assertEquals(28D, squid.search(key).getDouble((MetricDef) Metric.STATEMENTS));

  }
}
