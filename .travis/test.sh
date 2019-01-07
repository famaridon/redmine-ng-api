#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..

echo "Waiting wildfly to launch on 8080..."
while ! nc -z localhost 8080; do
  sleep 1 # wait for 1 second before check again
done
echo "wildfly launched"

mvn -P with-test-env test
result=$?

cd docker
docker-compose stop

exit ${result}