#! /bin/bash

. .travis/test-configuration.sh

cd docker
docker-compose -f docker-compose-test.yml pull
docker-compose -f docker-compose-test.yml rm -f
docker-compose -f docker-compose-test.yml up --detach --force-recreate

cd ..

sleep $WAIT_DOCKER

mvn -Pjunit-itests test -B --fail-at-end
mvn -Pjunit-utests test -B --fail-at-end
mvn -Pjee-tests test -B --fail-at-end
mvn jacoco:merge --non-recursive
mvn sonar:sonar -B
result=$?

cd docker
docker-compose stop

cd ..
tar -czvf target/docker-compose.tar.gz --exclude='**/.gitkeep' --exclude='**/*.war' --exclude='**/config-*.json' --exclude='database/data/*' --exclude='front/www/*' docker/*

exit ${result}