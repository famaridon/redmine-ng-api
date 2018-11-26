#! /bin/bash

cp -f $JBOSS_HOME/configuration/standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml

$JBOSS_HOME/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0 --debug 5000