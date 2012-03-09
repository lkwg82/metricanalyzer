package de.lgohlke.analyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.Query;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceCodeSearchEngine;
import org.sonar.squid.measures.Metric;

import de.lgohlke.CommonStore;
import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Location;
import de.lgohlke.AST.Registry;
import de.lgohlke.analyzer.aggregationRules.MetricAggregationRuleAbstract;
import de.lgohlke.analyzer.aggregationRules.SetRelatedMethodsCountAggregationRule;
import de.lgohlke.analyzer.aggregationRules.SumDifferentMetricsAggregationRule;
import de.lgohlke.analyzer.aggregationRules.SumRelatedMethodsAggregationRule;
import de.lgohlke.analyzer.aggregationRules.SumRelatedMethodsOfDifferentClassesAggregationRule;
import de.lgohlke.io.RelatedTestsFinder;
import de.lgohlke.io.TestmethodContext;
import de.lgohlke.io.TestmethodContextFactory;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * <p>
 * RelatedCodeMetricAggregator class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class RelatedCodeMetricAggregator
{
  private final static MetricAggregationRuleAbstract[] metric2rulesMappingList = new MetricAggregationRuleAbstract[] {
    new SumRelatedMethodsAggregationRule(Metric.COMPLEXITY), new SumRelatedMethodsAggregationRule(Metric.LINES),
    new SumRelatedMethodsAggregationRule(Metric.COMMENT_LINES), new SumRelatedMethodsAggregationRule(Metric.LINES_OF_CODE),
    new SumRelatedMethodsAggregationRule(Metric.STATEMENTS),

    new SumRelatedMethodsAggregationRule(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION),
    new SumRelatedMethodsOfDifferentClassesAggregationRule(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION),

    new SumRelatedMethodsAggregationRule(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE),
    new SumRelatedMethodsOfDifferentClassesAggregationRule(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE),

    new SumRelatedMethodsAggregationRule(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE),
    new SumRelatedMethodsOfDifferentClassesAggregationRule(ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE),
    new SetRelatedMethodsCountAggregationRule(ASTMetrics.AST_RELATED_METHODS) };

  private final static MetricAggregationRuleAbstract[] aggregationRules        = new MetricAggregationRuleAbstract[] { //
    new SumDifferentMetricsAggregationRule(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION, ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION,
        ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION),//
        new SumDifferentMetricsAggregationRule(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE, ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE,
            ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE),//
            new SumDifferentMetricsAggregationRule(ASTMetrics.AGGREGATE_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE,
                ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE) };

  private final SourceCodeSearchEngine                 searchEngine;
  private final MutablePicoContainer                   pico;
  private RelatedTestsFinder                           finder;
  private Registry registry;

  private volatile int                                 count                   = 0;
  private volatile int                                 step                    = 0;
  private final int                                    precentStep             = 5;

  /**
   * <p>
   * Constructor for RelatedCodeMetricAggregator.
   * </p>
   * 
   * @param testClasses
   *          a {@link java.util.List} object.
   * @param searchEngine
   *          a {@link org.sonar.squid.api.SourceCodeSearchEngine} object.
   * @param locationList
   *          a {@link java.util.List} object.
   * @param classes
   *          a {@link java.util.List} object.
   *          <p/>
   *          TODO remove registry, only needed to handle QDox bug
   */
  public RelatedCodeMetricAggregator(final MutablePicoContainer pico, final SourceCodeSearchEngine searchEngine, final List<Location> locationList,
      final Registry registry)
  {
    this.pico = pico;
    this.searchEngine = searchEngine;this.registry=registry;

    finder = new RelatedTestsFinder(pico, locationList, registry);
    pico.addComponent(finder);
  }

  /**
   * <p>
   * doAggregation.
   * </p>
   * 
   * @throws java.lang.InstantiationException
   *           if any.
   * @throws java.lang.IllegalAccessException
   *           if any.
   */
  public void doAggregation() throws InstantiationException, IllegalAccessException
  {
    if (finder.getResult() == null)
    {
      log.debug("init finder");
      finder.find();
      log.debug("finished finder");
    }

    // debugSE(searchEngine);

    // ThreadPool parallelPool = new ThreadPool(1);
    // final ThreadPool sequentialPool = new ThreadPool(1);

    // final MetricAccessor accessor = new MetricAccessor(searchEngine);
    final int size = pico.getComponent(CommonStore.class).getTestClasses().size();
    count = 0;

    @Data
    class Element<T, V>
    {
      private final T context;
      private final V location;
    }

    final Queue<Element<TestmethodContext, Location>> queue = new ConcurrentLinkedQueue<Element<TestmethodContext, Location>>();

    for (final TestClass testClass : pico.getComponent(CommonStore.class).getTestClasses())
    {
      // parallelPool.submit(new Runnable()
      // {
      //
      // @Override
      // public void run()
      // {
      // TODO: make a class for displaying progressing
      count++;
      if ((((count * 100) / size) % precentStep) == 0)
      {
        if (step != ((count * 100) / size / precentStep))
        {
          step = (count * 100) / size / precentStep;
          int progress = (count * 100) / size;
          log.info(count + "/" + size + " " + progress + "% testclass: " + testClass.getClazz().getName());
        }
      }

      if (log.isDebugEnabled())
      {
        log.debug("handling class : " + testClass.getClazz().getName());
      }
      for (TestMethod testMethod : testClass.getTests())
      {
        if (log.isDebugEnabled())
        {
          log.debug("  handling test : " + testMethod);
        }
        final TestmethodContext result = TestmethodContextFactory.createByTest(finder, testMethod);
        final Location location = result.getLocationMap().get(new JavaMethodHashed(testMethod.getMethod()));

        queue.add(new Element<TestmethodContext, Location>(result, location));
      }
      // }
      // });
    }

    // parallelPool.waitForPoolEmpty();

    log.info("aggregating");
    for (final Element<TestmethodContext, Location> e : queue)
    {

      if (log.isDebugEnabled())
      {
        log.debug("aggregating metrics for " + e.context.getTestMethod());
      }

      if (e.location == null)
      {
        log.error("NPE: " + e.context.getTestMethod());
      }
      else
      {
        MetricAccessor accessor = new MetricAccessor(searchEngine);
        // set the number of related methods
        final SourceCode code = new LocationFixer(searchEngine).fixLocation(e.location);
        if (code == null)
        {
          log.error("this class could not be found: " + e.location.getKey());
          // throw new NullPointerException("this class could not be found: " + location.getKey());
        }
        else
        {
          // log.info("testmethod: " + e.context.getTestMethod());

          accessor.setTestmethodContext(e.context);
          accessor.setLocationKey(e.location.getKey());
          /**
           * TODO topological sort dependant metrics:
           * http://tawani.blogspot.com/2009/02/topological-sorting-and-cyclic.html
           */

          /*
           * related methods aggregating
           */
          for (MetricAggregationRuleAbstract rule : metric2rulesMappingList)
          {
            rule.doAggregation(accessor);
          }

          /*
           * different metrics aggregating into one
           */
          for (MetricAggregationRuleAbstract rule : aggregationRules)
          {
            rule.doAggregation(accessor);
          }

          /**
           * (LINES_OF_CODE + CLASS_VAR_DEFS) / AGGREGATED TYPE_DISTANCE
           */
          new MetricAggregationRuleAbstract(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE_PER_LOC)
          {
            @Override
            public void doAggregation(final MetricAccessor accessor)
            {
              SourceCode testMethod = accessor.retrieveTestMethod();

              double aggregateTypeDistance = testMethod.getDouble(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE);
              double ast_class_vard_defs = testMethod.getDouble(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION);
              double loc = testMethod.getDouble(Metric.LINES_OF_CODE);
              double asserts = testMethod.getDouble(ASTMetrics.AST_NUMBER_OF_ASSERT_STATEMENTS);

              Double result =  aggregateTypeDistance / ((ast_class_vard_defs + loc) - asserts);

              //              registry.addDescription(key, metric, describe)
              testMethod.setMeasure(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE_PER_LOC, result);
            }
          }.doAggregation(accessor);
        }
      }
    }

    // parallelPool.waitForShutdown();
    // sequentialPool.waitForShutdown();
  }

  public static void debugSE(final SourceCodeSearchEngine searchEngine2)
  {
    Collection<SourceCode> results = searchEngine2.search(new Query()
    {

      @Override
      public boolean match(final SourceCode unit)
      {
        return true; // unit.toString().contains("$");
      }
    });

    List<String> codes = new ArrayList<String>(results.size());
    for (SourceCode code : results)
    {
      codes.add(code + "");
    }
    Collections.sort(codes);
    for (String code : codes)
    {
      log.debug("SE-debug " + code);
    }

  }
}
