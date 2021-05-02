#!/bin/bash

# original : https://github.com/kaihendry/bank-test/blob/master/jmeter.sh

NAME="jmeter"
IMAGE="justb4/jmeter:5.3"

test "$1" || exit
echo Running Jmeter benchmark \"$1\" $(uname -a)

docker run --rm --name ${NAME} --network host -i -v ${PWD}:${PWD} -w ${PWD} ${IMAGE} -JHOST=localhost -JPORT=80 -JNUM_USERS=10000 \
	-n -t test-bank.jmx -l test-results/$1.csv -e -o test-results/$1