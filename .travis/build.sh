#! /bin/bash

main() {
	echo "Start building and testing"
	. .travis/test-configuration.sh
	start_docker
	sleep $WAIT_DOCKER
	build_and_test
	build_result=$?
	stop_docker
	if [[ ${build_result} -eq 0 ]]; then
		archive
	fi
}

build_and_test() {
	mvn -B --fail-at-end -q -Pjunit,junit-itests test
	if [[ $? -ne 0 ]]; then
		echo "Integration test failed."
		return 1
	fi
	mvn -B --fail-at-end -q -Pjunit,junit-utests test
	if [[ $? -ne 0 ]]; then
		echo "Unit test failed."
		return 2
	fi
	mvn -B --fail-at-end -q -Parquillian,jee-tests verify
	if [[ $? -ne 0 ]]; then
		echo "JEE test failed."
		return 3
	fi
	mvn -Pjacoco-merge jacoco:merge --non-recursive
	if [[ $? -ne 0 ]]; then
		echo "Can't merge jacoco reports"
		return 4
	fi
	mvn sonar:sonar -B
	if [[ $? -ne 0 ]]; then
		echo "Sonar have failed."
		return 5
	fi
}

archive() {
	tar -czvf target/docker-compose.tar.gz --exclude='**/.gitkeep' --exclude='**/*.war' --exclude='**/config-*.json' --exclude='database/data/*' --exclude='front/www/*' docker/*
}

start_docker() {
	cd docker
	docker-compose -f docker-compose-test.yml pull
	docker-compose -f docker-compose-test.yml rm -f
	docker-compose -f docker-compose-test.yml up --detach --force-recreate
	cd ..
}

stop_docker() {
	cd docker
	docker-compose -f docker-compose-test.yml stop
	cd ..
}
declare -i build_result=100

main

exit ${build_result}





