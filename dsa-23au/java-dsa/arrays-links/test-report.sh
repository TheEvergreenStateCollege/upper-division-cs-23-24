#!/bin/sh

# Tests generated into /target/site/index.html
#
mvn surefire-report:report -Daggregate=true
cp target/site/surefire-report.html ../../notes/docs/week-04/tests-array-links.html
