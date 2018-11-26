#! /bin/bash

. test-configuration.sh

cd docker
docker-compose up -d

cd ..
mvn -P with-test-env test

cd docker
docker-compose stop