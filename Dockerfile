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

FROM python:2.7-slim

MAINTAINER "安田 アポロ <apolo.yasuda@ge.com>"

COPY ./ ~/

WORKDIR ~/

ENV CF_API_URI={CF_API_URI} CF_USERNAME={CF_USERNAME} CF_PASSWORD={CF_PASSWORD} CF_ORG="{CF_ORG}" CF_SPC="{CF_SPC}" EC_SDK_DOWNLOAD={EC_SDK_DOWNLOAD} EC_SHARED_DOMAIN={EC_SHARED_DOMAIN} EC_SERVICE={EC_SERVICE} EC_SERVICE_TKN={EC_SERVICE_TKN} EC_SERVICE_ZONE={EC_SERVICE_ZONE} EC_UAA={EC_UAA} EC_UAA_CID={EC_UAA_CID} EC_UAA_CSC={EC_UAA_CSC} EC_PSQL_HOST={EC_PSQL_HOST} PG_CONNSTR="{PG_CONNSTR}" EC_SERVICE_INST="{EC_SERVICE_INST}" PG_SERVICE_INST="{PG_SERVICE_INST}" EC_CLIENT_ID={EC_CLIENT_ID} EC_SERVER_ID={EC_SERVER_ID} EC_TEST_LOOP={EC_TEST_LOOP} EC_TEST_ROW={EC_TEST_ROW} EC_TEST_THREAD={EC_TEST_THREAD} EC_SERVER_INST={EC_SERVER_INST} EC_GATEWAY_INST={EC_GATEWAY_INST} EC_SCALE_NUM={EC_SCALE_NUM} EC_TEST_BREAK={EC_TEST_BREAK} EC_GROUP_ID={EC_GROUP_ID}

#ENV CF_API_URI={CF_API_URI} CF_USERNAME={CF_USERNAME} CF_PASSWORD={CF_PASSWORD} CF_ORG="{CF_ORG}" CF_SPC="{CF_SPC}" EC_SDK_DOWNLOAD={EC_SDK_DOWNLOAD} EC_SHARED_DOMAIN={EC_SHARED_DOMAIN} EC_SERVICE={EC_SERVICE} EC_SERVICE_TKN={EC_SERVICE_TKN} EC_SERVICE_ZONE={EC_SERVICE_ZONE} EC_UAA={EC_UAA} EC_UAA_CID={EC_UAA_CID} EC_UAA_CSC={EC_UAA_CSC} EC_PSQL_HOST={EC_PSQL_HOST} PG_CONNSTR="{PG_CONNSTR}" https_proxy={PROXY} http_proxy={PROXY} EC_SERVICE_INST="{EC_SERVICE_INST}" PG_SERVICE_INST="{PG_SERVICE_INST}" EC_CLIENT_ID={EC_CLIENT_ID} EC_SERVER_ID={EC_SERVER_ID} EC_TEST_LOOP={EC_TEST_LOOP} EC_TEST_ROW={EC_TEST_ROW} EC_TEST_THREAD={EC_TEST_THREAD} EC_SERVER_INST={EC_SERVER_INST} EC_GATEWAY_INST={EC_GATEWAY_INST} EC_SCALE_NUM={EC_SCALE_NUM} EC_TEST_BREAK={EC_TEST_BREAK}

#RUN apt-get install apt-transport-https
RUN apt-get update && apt-get install -y apt-transport-https
RUN apt-get update && apt-get install -y libpq-dev gcc
# need gcc to compile psycopg2
RUN pip2 install psycopg2
RUN pip2 install pyyaml

RUN mv cf /usr/local/bin
RUN cf --version

RUN ls -al && pwd
#RUN python2 -u ./ec_test.py

#RUN chmod +x $GOPATH/src/{DHOME}
# No need to listen to a port. busted!
#EXPOSE 8080