#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml pull
docker-compose -f docker-compose-test.yml rm -f
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..

sleep $WAIT_DOCKER

mvn -Pwith-test-env clean install sonar:sonar -B --fail-at-end
result=$?

cd docker
docker-compose stop

tar -czvf target/docker-compose.tar.gz --exclude='**/.gitkeep' --exclude='**/*.war' --exclude='**/config-*.json' --exclude='database/data/*' --exclude='front/www/*' docker/*

exit ${result}