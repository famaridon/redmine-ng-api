#!/bin/bash

# Performs any provisioning needed for a clean build.
#
# This script is meant to be used either directly
# as a `before_install` step such that the next step
# in the Travis build have the environment properly
# setup.
#
# The script is also handy for debugging - SSH into
# the machine and then call `./.travis/setup.sh` to
# have all dependencies set.

set -o errexit

main() {
  setup_dependencies
  update_mvn "3.6.0"

  echo "INFO:
  Done! Finished setting up Travis-CI machine.
  "
}

# Takes care of updating any dependencies that the
# machine needs.
setup_dependencies() {
  echo "INFO:
  Setting up dependencies.
  "

  sudo apt-get -qq update -y
  sudo apt-get -qq install --only-upgrade docker-ce -y

  docker info

  sudo curl -L "https://github.com/docker/compose/releases/download/1.23.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

  docker version
  docker-compose version
}

update_mvn() {
  version=$1
  wget -nv http://apache.mirror.gtcomm.net/maven/maven-3/$version/binaries/apache-maven-$version-bin.tar.gz
  sudo mkdir /usr/local/maven-$version
  sudo tar xzvf apache-maven-$version-bin.tar.gz
  sudo mv apache-maven-$version/* /usr/local/maven-$version/
  sudo rm -rf /usr/local/maven-3.5.2
  sudo ln -s /usr/local/maven-$version /usr/local/maven-3.5.2
  ls -l /usr/local/maven-3.5.2
  mvn -v
 }

main