# news #
2013-03-12 better analysis possible http://blog.lgohlke.de/2013/02/line-coverage-per-test-by-jacoco.html

# idea #

Help finding bugs by sorting tests according their priority and present the most significant at the top of the list.

# implementation #

Used software metrics to calculate the priority by defining a so called 'type distance'. Implemented as test listener and executed in case there are more than one failed tests.


see the [Quickstart](Quickstart.md)
also see the [FullDemo](FullDemo.md)

uses:
  * sonar http://www.sonarsource.org/
  * qdox http://qdox.codehaus.org/