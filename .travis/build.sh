#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml pull
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..

sleep $WAIT_DOCKER

mvn -P with-test-env clean jacoco:prepare-agent install sonar:sonar -B --fail-at-end
result=$?

cd docker
docker-compose stop

if $result; then
    tar -czvf target/docker-compose.tar.gz docker/*
fi

exit ${result}