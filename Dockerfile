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

USER root
COPY ./cf /usr/local/bin
COPY ./__init__.py ./
WORKDIR ~/

#RUN apt-get install apt-transport-https
RUN apt-get update && apt-get install -y apt-transport-https
RUN apt-get update && apt-get install -y libpq-dev gcc
# need gcc to compile psycopg2
RUN pip2 install psycopg2
RUN pip2 install pyyaml
RUN pip2 install docker

RUN cf --version

#USER 1000

VOLUME /benchmark

#RUN ls -al && pwd
CMD ls -la /benchmark$DIND_PATH && cd /benchmark$DIND_PATH && chmod -R 755 ./ && \
    python2 -u ./test.py

#RUN chmod +x $GOPATH/src/{DHOME}
# No need to listen to a port. busted!
#EXPOSE 8080