FROM python:3-alpine

ENV HTTP_PROXY http://PITC-Zscaler-Americas-Alpharetta3pr.proxy.corporate.ge.com:80
ENV HTTPS_PROXY http://PITC-Zscaler-Americas-Alpharetta3pr.proxy.corporate.ge.com:80
ENV http_proxy http://PITC-Zscaler-Americas-Alpharetta3pr.proxy.corporate.ge.com:80
ENV https_proxy http://PITC-Zscaler-Americas-Alpharetta3pr.proxy.corporate.ge.com:80

ARG HOST
ARG PORT
ARG USER
ARG PASSWORD
ARG DBNAME
ARG DATABASE
ARG OPERATION

ADD db_main.py /
ADD mysql_operations.py /
ADD postgres_operations.py /

#yum install gcc python-setuptools python-devel postgresql-devel
RUN apk update && apk add postgresql-dev gcc python3-dev musl-dev
#RUN pip install pystrich
RUN pip install mysql-connector-python-rf
RUN pip install psycopg2


CMD python db_main.py --host $HOST --port $PORT --user $USER --password $PASSWORD --database $DATABASE --operation $OPERATION  --dbname $DBNAME


# How to build the image -
# docker build -t ec-mysql-connector .

# How to run the image -
# docker run -e "HOST=host.docker.internal" -e "PORT=5432" -e "USER=postgres" -e "PASSWORD=postgres" -e "DATABASE=postgres" -e "OPERATION=bulkdata" -e "DBNAME=scott" ec-db