set MAVEN_OPTS= -verbose:gc -Xmx320m -XX:NewRatio=4 -Xss1m -XX:+PrintTenuringDistribution -XX:SurvivorRatio=1
mvn clean install -Ptask1 
mvn clean install -Ptask1 -Dobj
mvn clean install -Ptask1 -Dscalar