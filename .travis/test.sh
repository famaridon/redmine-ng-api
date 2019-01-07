#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..
mvn -P with-test-env test

cd docker
docker-compose stop