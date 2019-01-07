#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..

sleep 30

mvn -P with-test-env test
result=$?

cd docker
docker-compose stop

exit ${result}