#! /bin/bash

# Jacoco
mkdir -p $JBOSS_HOME/jacoco
curl -L "http://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/$JACOCO_VERSION/jacoco-$JACOCO_VERSION.zip" \
    --output "$JBOSS_HOME/jacoco/jacoco-$JACOCO_VERSION.zip"

md5sum $JBOSS_HOME/jacoco/jacoco-$JACOCO_VERSION.zip | grep $JACOCO_MD5
unzip -q $JBOSS_HOME/jacoco/jacoco-$JACOCO_VERSION.zip -d $JBOSS_HOME/jacoco


