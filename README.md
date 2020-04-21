# ec-test-automation-II
## Enterprise-Connect Test Automation
Execute the root build.sh will produce a docker image which is the foundation of the related test in the subdirectories, respectively. The artifact image will be checked-in the designated dtr repo in the end of the script.  

### Goal
- To test EC usage model `postgres(cf)<-server(cf)->gateway(aws-vpc)<-client(jenkins)<-request(jenkins)`
- Standardise the usage compatibility.
- Beginning of May 1st '18, effective immidiately, we will be implementing and maintaining EC One, the [promise of compatibility](#) for the future of EC core development.

## How to test
### test out a use case in the subfirectory

```shell
#clone this repo
https://github.build.ge.com/Enterprise-Connect/ec-tst-automation-II.git

#build the test foundry image and push it to the dtr
./build.sh

#pull the image and readying for the test
docker pull dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta

#run a test in the subdirectory
docker run --env-file ./env.list -v \"${theDIR}\":/benchmark -u root -i --name ec-agent-tesetsuite_${BUILD_NUMBER} dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta 
```

## Revision Matrix
Env | Rev. (prefix) | Download/Release | CF Service | CF Broker | SDK/Plugins | Tools | Build | QA
--- | --- | --- | --- | --- | --- | --- | --- | ---
DC Azure | v1.1 | [edo.0](https://github.com/Enterprise-connect/sdk/tree/v1.1.edo.0/dist)<br /><sup>[Release Note](https://github.com/Enterprise-connect/sdk/releases/tag/v1.1.edo.0) </sup>| not required | not required | deferred | self-provisioning/daemon mode | [CI](https://travis-ci.com/github/Enterprise-connect/build) | [Integration](https://travis-ci.com/github/Enterprise-connect/qa)
DC VPC | v1 | [hokkaido.212](https://github.com/Enterprise-connect/ec-x-sdk/tree/v1.hokkaido.212/dist)<br /><sup>[Release Note](https://github.com/Enterprise-connect/ec-x-sdk/releases/tag/v1.hokkaido.212) </sup>| kyushu.145 [project access](https://github.build.ge.com/Enterprise-Connect/ec-service/tree/v1.kyushu.145) | okinawa.8 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-predix-service-broker/tree/v1.okinawa.8) | [v1.hokkaido.212](https://github.com/Enterprise-connect/ec-x-sdk/tree/v1.hokkaido.212/plugins) | [Cloud Foundry Only](https://i.ci.build.ge.com/rtc5ryln/ci/job/Enterprise-Connect/job/EC%20Phase%20II%20Automation/) | [CI](http://localhost:8080/job/EC/job/Core/) | [Integration](http://localhost:8080/job/EC/job/QA/)
DC VPC (Watcher) | v1.1beta | [kanagawa.2](#)<br /><sup>[Release Note](#)</sup> | CF De-commision | CF De-commision | [kanagawa.2](#) | xcalrii@[v2beta.detroit.80](http://xcalr.apps.ge.com/v2beta/swagger-ui.html) | [CI](http://localhost:8080/job/EC/job/Core/) | [Integration](http://localhost:8080/job/EC/job/QA/)
Predix CF1/GovCloud | v1 | [hokkaido.212](https://github.com/Enterprise-connect/ec-x-sdk/tree/v1.hokkaido.212/dist)<br /><sup>[Release Note](https://github.com/Enterprise-connect/ec-x-sdk/releases/tag/v1.hokkaido.212)</sup> | kyushu.145 [project access](https://github.build.ge.com/Enterprise-Connect/ec-service/tree/v1.kyushu.145) | okinawa.8 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-predix-service-broker/tree/v1.okinawa.8) | [v1.hokkaido.212](https://github.com/Enterprise-connect/ec-x-sdk/tree/v1.hokkaido.212/plugins) | [Cloud Foundry Only](https://i.ci.build.ge.com/rtc5ryln/ci/job/Enterprise-Connect/job/EC%20Phase%20II%20Automation/) | [CI](http://localhost:8080/job/EC/job/Core/) | [Integration](http://localhost:8080/job/EC/job/QA/)
Power PoC/Predix CF3 | v1beta | [v1beta.fukuoka.1724](https://github.com/Enterprise-connect/ec-x-sdk/tree/v1beta.fukuoka.1724/dist)<br /><sup>[Release Note](https://github.com/Enterprise-connect/ec-x-sdk/releases/tag/v1beta.fukuoka.1724)</sup> | sendai.1079 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-service/tree/v1beta.sendai.1079) | okayama.49 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-predix-service-broker/tree/v1beta.okayama.49) | [v1beta.fukuoka.1724](https://github.com/Enterprise-connect/ec-x-sdk/tree/v1beta.fukuoka.1724/plugins) | xcalrii@[v2beta.detroit.80](http://xcalr.apps.ge.com/v2beta/swagger-ui.html) | [CI](http://localhost:8080/job/EC/job/Core/) | [Integration](http://localhost:8080/job/EC/job/QA/)

## Test Workflow
1. Bootstrap env; download the latest agent artifact.
2. Deploy gateway/server in CF.
3. Scale gateway/server to 5 instances each.
4. Deploy client in Jenkins.
5. Create 5 worker threads.
6. Create a Postgres connection in the thread.
7. Query 100k rows against the postgres.
8. Destroy the connection.
9. Repeat step 6.
 
### Configuration (To-be-updated)
#### Server
```script
./ecagent_linux_var -mod server -aid <server-id> -hst <gateway-url>/agent /
-rht <amazon-postgres-url, schema excluded> -rpt <amazon-postgres-port> /
-cid <uaa-client-id> -csc <uaa-client-secret> -oa2 <uaa-url>/oauth/token /
-dur 300 -dbg -hca ${PORT} -zon <ec-zone-id> -sst <ec-cf-service-url>
```
#### Gateway
```script
./ecagent_linux_var -mod gateway -lpt ${PORT} -zon <ec-zone-id> /
-sst <ec-cf-service-url> -dbg -tkn <ec-admin-token-from-vcap>
```
#### Client
```script
./ecagent_linux_var -mod client -aid <client-id> -hst <gateway-url>/agent /
-lpt 7990 -tid <server-id> -oa2 <uaa-url>/oauth/token -cid <uaa-client-id> /
-csc <uaa-client-secret> -dur 300 -dbg -pxy <local-proxy, needed from Jenkins to CF>
```

### Requirement
- Docker 2.7-slim
- python 2.6+
- psycopg2
- pip2
- EC Agent 1.86+
- EC Service nara.66+
- Cloud Foundry (Predix East/Select)

### Settings
- x5 concurrent worker-thread/EC connection with PostgresSQL.
- x5 scaled gateway instances.
- x5 scaled server instances.
- x24 hour repetitive test.
- Amazon Postgres (CF3, db-0f1c7a77-1ccf-4cb1-9817-0c0e39f0bd1d.c7uxaqxgfov3.us-west-2.rds.amazonaws.com)

### Benchmark
- Averaged x100k rows/1.2 secs in CF1.
- Averaged x100k rows/1.2 secs in CF3.
- Azure. Pending.
- AWS. Pending.

