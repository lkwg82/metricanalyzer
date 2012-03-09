package de.lgohlke.AST.calculators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

public enum DISTANCE_RULE
{
  FIRST
  {
    @Override
    public String chooseAst(final String currentClass, final List<String> typeSet)
    {
      return typeSet.size() > 0 ? typeSet.get(0) : null;
    }
  },
  LOWEST
  {
    @Override
    public String chooseAst(final String currentClass, final List<String> typeSet)
    {
      return typeSet.size() > 0 ? typeSet.get(new StatsHelper(currentClass, typeSet).findLowest()) : null;
    }
  },
  HIGHEST
  {
    @Override
    public String chooseAst(final String currentClass, final List<String> typeSet)
    {
      return typeSet.size() > 0 ? typeSet.get(new StatsHelper(currentClass, typeSet).findHighest()) : null;
    }
  },
  GEOMETRIC_MEAN
  {
    @Override
    public String chooseAst(final String currentClass, final List<String> typeSet)
    {
      return typeSet.size() > 0 ? typeSet.get(new StatsHelper(currentClass, typeSet).findGeometricMean()) : null;
    }
  },
  ARITHMETIC_MEAN
  {
    @Override
    public String chooseAst(final String currentClass, final List<String> typeSet)
    {
      return typeSet.size() > 0 ? typeSet.get(new StatsHelper(currentClass, typeSet).findMean()) : null;
    }
  };
  // FIRST_MAX;

  public abstract String chooseAst(final String currentClass, List<String> typeSet);

  private static class StatsHelper
  {

    private final List<Double>          distanceVector;
    private final DescriptiveStatistics stats;
    private final DescriptiveStatistics geometricMeanStats;

    public StatsHelper(final String currentClass, final List<String> typeSet)
    {
      distanceVector = new ArrayList<Double>(typeSet.size());

      for (String clazz : typeSet)
      {
        distanceVector.add(new TypeDistanceCalculator(currentClass, clazz).calculate());
      }

      stats = new DescriptiveStatistics(typeSet.size());
      geometricMeanStats = new DescriptiveStatistics(typeSet.size());
      for (Double v : distanceVector)
      {
        stats.addValue(v);
        // 0 wird auf 1 gehoben, weil geometrische Mittel multiplikativ ist und dann immer 0 herauskäme
        geometricMeanStats.addValue(Double.compare(v, 0D) == 0 ? 1D : v);
      }

      // System.out.println("---");
      // System.out.println(stats.getMax() + " : " + findIndexWithValue(stats.getMax(), distanceVector));
      // System.out.println(stats.getMin() + " : " + findIndexWithValue(stats.getMin(), distanceVector));
      // System.out.println(geometricMeanStats.getGeometricMean() + " : " +
      // findClosestIndex(geometricMeanStats.getGeometricMean()));
      // System.out.println(stats.getMean() + " : " + findClosestIndex(stats.getMean()));
    }

    /**
     * gibt den ersten Index zurück, der mit dem übergebenen Wert übereinstimmt
     * 
     * @param value
     * @return
     */
    private int findIndexWithValue(final double value, final List<Double> list)
    {
      int result = -1;
      for (int i = 0; (i < list.size()) && (result == -1); i++)
      {
        Double _value = list.get(i);
        if (Double.compare(_value, value) == 0)
        {
          result = i;
        }
      }
      return result;
    }

    /**
     * gibt den Index vom (ersten) höchsten Eintrag wieder
     * 
     * @return
     */
    public int findHighest()
    {
      return findIndexWithValue(stats.getMax(), distanceVector);
    }

    /**
     * gibt den Index vom (ersten) niedrigsten Eintrag wieder
     * 
     * @return
     */
    public int findLowest()
    {
      return findIndexWithValue(stats.getMin(), distanceVector);
    }

    /**
     * gibt den Index vom Eintrag des dem Geometrischen Mittels am nähesten Elementes wieder
     * 
     * @return
     */
    public int findGeometricMean()
    {
      return findClosestIndex(geometricMeanStats.getGeometricMean());
    }

    private int findClosestIndex(final double value)
    {
      // Abweichungen ermitteln
      DescriptiveStatistics stats2 = new DescriptiveStatistics();
      Double[] distances = new Double[distanceVector.size()];
      for (int i = 0; i < distances.length; i++)
      {
        double diff = Math.abs(value - distanceVector.get(i));
        stats2.addValue(diff);
        distances[i] = diff;
      }

      // kleinste erste Abweichung und davon der Index
      return findIndexWithValue(stats2.getMin(), Arrays.asList(distances));
    }

    /**
     * gibt den Index vom Eintrag des arithmetischen Mittels wieder
     * 
     * @return
     */
    public int findMean()
    {
      return findClosestIndex(stats.getMean());
    }

  }
}
