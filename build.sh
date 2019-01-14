#!/bin/bash
#
#  Copyright (c) 2016 General Electric Company. All rights reserved.
#
#  The copyright to the computer software herein is the property of
#  General Electric Company. The software may be used and/or copied only
#  with the written permission of General Electric Company or in accordance
#  with the terms and conditions stipulated in the agreement/contract
#  under which the software has been supplied.
#
#  author: apolo.yasuda@ge.com
#

#set -x
set -e

#let's do docker way
function docker_run () {

    docker build -t ${ARTIFACT}_img:v1beta .
    
    #CID=$(docker ps -aqf "name=${ARTIFACT}_inst")
    IID=$(docker images -q ${ARTIFACT}_img:v1beta)

    docker login dtr.predix.io -u ${DTRUSR} -p ${DTRPWD}

    docker tag ${ARTIFACT}_img:v1beta dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta

    docker push dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta
    
    #docker rmi ${IID}
}

ARTIFACT=ec-int-test

#unset_proxy
curl -k -L "https://cli.run.pivotal.io/stable?release=linux64-binary&source=github" | tar -zx
#no_docker_run
docker_run
