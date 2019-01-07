#! /bin/bash

. test-configuration.sh

cd docker
docker-compose up -f docker-compose-test.yml -d

cd ..
mvn -P with-test-env test

cd docker
docker-compose stop