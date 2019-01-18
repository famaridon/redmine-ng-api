#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..

sleep $WAIT_DOCKER

mvn -P with-test-env clean jacoco:prepare-agent install sonar:sonar -B
result=$?

cd docker
docker-compose stop

exit ${result}