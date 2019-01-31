#! /bin/bash

cp -f $JBOSS_HOME/configuration/config*.json $JBOSS_HOME
cp -f $JBOSS_HOME/configuration/standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml
cp -f $JBOSS_HOME/staging/* $JBOSS_HOME/standalone/deployments/


$JBOSS_HOME/wait

$JBOSS_HOME/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0 --debug 5000