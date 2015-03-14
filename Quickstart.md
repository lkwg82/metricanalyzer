# quick start #

## standalone analysis ##
  1. checkout the trunk with
```
svn checkout http://metricanalyzer.googlecode.com/svn/trunk/ metricanalyzer-read-only
```
  1. run a simple analysis with
```
mvn -P analyzer compile -Dmaven-project-path=.
```
  1. open the analysis from ./target/analysis/tests.html or
  1. open the ordered list from ./target/analysis/orderedList.html

see the pom.xml in the profile 'analyzer' for the internal calls

## run as testng listener ##

  1. have a maven project
  1. add these repositories (sry it is quick and dirty)
```
    <repositories>
        <repository>
            <id>de.lgohlke.maven3</id>
            <url>http://repos3.lgohlke.de</url>
        </repository>
        <repository>
            <id>de.lgohlke.maven3.snaps</id>
            <url>http://snapshots3.lgohlke.de</url>
        </repository>
        <repository>
            <id>de.lgohlke.maven2.snaps</id>
            <url>http://snapshots.lgohlke.de/maven2</url>
        </repository>
        <repository>
            <id>de.lgohlke.maven2</id>
            <url>http://repos.lgohlke.de/maven2</url>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>de.lgohlke.maven2.plugins</id>
            <url>http://repos.lgohlke.de</url>
        </pluginRepository>
        <pluginRepository>
            <id>de.lgohlke.maven3.plugins</id>
            <url>http://repos3.lgohlke.de</url>
        </pluginRepository>
    </pluginRepositories>
```
  1. add the testng listener
```
    <build>
       <plugins>
          <plugin>
                <artifactId>maven-surefire-plugin</artifactId>              
                <version>2.11</version>
                <configuration>
                    <properties>
                        <property>
                            <name>listener</name>             
                            <value>de.lgohlke.runner.TestNGAnalysisRunner</value>
                        </property>
                    </properties>
                    <parallel>class</parallel>
                    <threadCount>10</threadCount>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
  1. add the metric analyzer as dependency
```
        <dependency>
            <groupId>de.lgohlke.master</groupId>
            <artifactId>MetricsAnalyzer</artifactId>
            <version>0.5</version>
            <exclusions>
                <exclusion>
                    <groupId>ch.qos.logback</groupId>
                    <artifactId>logback-classic</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```
  1. run the tests let at least 2 tests fail
  1. see the hint at the end of the a test run with failed tests