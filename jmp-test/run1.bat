set MAVEN_OPTS= -verbose:gc -Xmx1024m -Xss2m -XX:+PrintTenuringDistribution -XX:SurvivorRatio=5
mvn clean install -Ptask1