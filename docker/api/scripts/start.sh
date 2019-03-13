#! /bin/bash

cp -f $JBOSS_HOME/configuration/config*.json $JBOSS_HOME
cp -f $JBOSS_HOME/configuration/standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml
cp -f $JBOSS_HOME/staging/* $JBOSS_HOME/standalone/deployments/

cp -f $JBOSS_HOME/bin/standalone.conf $RUN_CONF

if [ "$JACOCO" = "true" ]; then
	echo "Enable Jacoco on port $JACOCO_PORT"
  echo "JAVA_OPTS=\"\$JAVA_OPTS -javaagent:$JBOSS_HOME/jacoco/lib/jacocoagent.jar=output=tcpserver,address=*,port=$JACOCO_PORT\"">> $RUN_CONF
fi

if [ "$JPROFILER" = "true" ]; then
	echo "Enable JProfiler on port $JPROFILER_PORT"
  echo "JAVA_OPTS=\"\$JAVA_OPTS -agentpath:$JBOSS_HOME/jprofiler/bin/linux-x64/libjprofilerti.so=port=$JPROFILER_PORT,nowait\"">> $RUN_CONF
fi

if [ "$DEBUG" = "true" ]; then
	echo "Enable Debug on port $DEBUG_PORT"
  echo "JAVA_OPTS=\"\$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$DEBUG_PORT\"">> $RUN_CONF
fi

$JBOSS_HOME/wait

$JBOSS_HOME/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0
