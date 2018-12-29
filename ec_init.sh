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

#no proxy env
#export https_proxy=${PROXY}

function unset_proxy (){
    #weird corporate setting
    unset HTTP_PROXY
    unset HTTPS_PROXY
    unset http_proxy
    unset https_proxy
    eval echo $(git config --global --unset http.proxy)
    eval echo $(git config --global --unset https.proxy)
}

#let's do docker way
function docker_run () {

    #logout from existing docker login
    #docker logout
    
    docker build -t ${ARTIFACT}_img:v1beta .
    #docker run -i --name ${ARTIFACT}_inst ${ARTIFACT}_img

    #CID=$(docker ps -aqf "name=${ARTIFACT}_inst")
    IID=$(docker images -q ${ARTIFACT}_img:v1beta)

    #git clone ${REPO} ./${DIST}/
    
    #docker cp ${CID}:/${DIST}/. ./${DIST}/bin/

    
    #docker rm ${CID}

    unset_proxy

    docker login dtr.predix.io -u ${DTRUSR} -p ${DTRPWD}

    docker tag ${ARTIFACT}_img:v1beta dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta

    docker push dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta
    
    #docker rmi ${IID}
}

ARTIFACT=ec-int-test
```
eval "sed -i -e 's#{CF_API_URI}#${CF_API_URI}#g' ./Dockerfile"
eval "sed -i -e 's#{CF_USERNAME}#${CF_USERNAME}#g' ./Dockerfile"
eval "sed -i -e 's#{CF_PASSWORD}#${CF_PASSWORD}#g' ./Dockerfile"
eval "sed -i -e 's#{CF_ORG}#${CF_ORG}#g' ./Dockerfile"
eval "sed -i -e 's#{CF_SPC}#${CF_SPC}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SDK_DOWNLOAD}#${EC_SDK_DOWNLOAD}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SHARED_DOMAIN}#${EC_SHARED_DOMAIN}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SERVICE}#${EC_SERVICE}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SERVICE_TKN}#${EC_SERVICE_TKN}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SERVICE_ZONE}#${EC_SERVICE_ZONE}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_UAA}#${EC_UAA}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_UAA_CID}#${EC_UAA_CID}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_UAA_CSC}#${EC_UAA_CSC}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_PSQL_HOST}#${EC_PSQL_HOST}#g' ./Dockerfile"
eval "sed -i -e 's#{PG_CONNSTR}#${PG_CONNSTR}#g' ./Dockerfile"
eval "sed -i -e 's#{PROXY}#${PROXY}#g' ./Dockerfile"
eval "sed -i -e 's#{PG_SERVICE_INST}#${PG_SERVICE_INST}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SERVICE_INST}#${EC_SERVICE_INST}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_CLIENT_ID}#${EC_CLIENT_ID}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SERVER_ID}#${EC_SERVER_ID}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_TEST_BREAK}#${EC_TEST_BREAK}#g' ./Dockerfile"

eval "sed -i -e 's#{EC_TEST_ROW}#${EC_TEST_ROW}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_TEST_LOOP}#${EC_TEST_LOOP}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_TEST_THREAD}#${EC_TEST_THREAD}#g' ./Dockerfile"

eval "sed -i -e 's#{EC_SERVER_INST}#${EC_SERVER_INST}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_GATEWAY_INST}#${EC_GATEWAY_INST}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_SCALE_NUM}#${EC_SCALE_NUM}#g' ./Dockerfile"
eval "sed -i -e 's#{EC_GROUP_ID}#${EC_GROUP_ID}#g' ./Dockerfile"
```

curl -L "https://cli.run.pivotal.io/stable?release=linux64-binary&source=github" | tar -zx
#no_docker_run
docker_run
