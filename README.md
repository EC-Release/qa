# ec-test-automation
Enterprise-Connect Test Automation

### Goal
- To test EC usage model `postgres(cf)<-server(cf)->gateway(cf)<-client(jenkins)<-request(jenkins)`
- Standardise the usage compatibility.

## Revision Matrix
Env | Rev. (prefix) | Agent | Service | CF Broker | SDK/Plugins | Tools
--- | --- | --- | --- | --- | --- | ---
Predix CF1 | v1 | [hokkaido.198](https://github.build.ge.com/Enterprise-Connect/ec-sdk/archive/v1.hokkaido.198.zip) | kyushu.145 [project access](https://github.build.ge.com/Enterprise-Connect/ec-service/tree/v1.kyushu.145) | okinawa.8 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-predix-service-broker/tree/v1.okinawa.8) | [v1.hokkaido.198](https://github.build.ge.com/Enterprise-Connect/ec-sdk/tree/v1.hokkaido.198/plugins) | [Blue-Green Upgrade Step1](http://10.227.87.157/jenkins/job/%E3%82%A4%E3%82%B7%20(EC)/job/Automation/job/Update/job/EC%20Service%20Update%20Step%201%20(CF1)/) [Blue-Green Upgrade Step2](http://10.227.87.157/jenkins/job/%E3%82%A4%E3%82%B7%20(EC)/job/Automation/job/Update/job/EC%20Service%20Update%20Step%202%20(CF1)/)
Predix CF3 | v1beta | [fukuoka.1240](https://github.build.ge.com/Enterprise-Connect/ec-sdk/archive/v1beta.fukuoka.1240.zip) | sendai.1079 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-service/tree/v1beta.sendai.1079) | okayama.49 [[project access]](https://github.build.ge.com/Enterprise-Connect/ec-predix-service-broker/tree/v1beta.okayama.49) | [v1beta.fukuoka.1240](https://github.build.ge.com/Enterprise-Connect/ec-sdk/tree/v1beta.fukuoka.1240/plugins) | [Blue-Green Upgrade Step1](http://10.227.87.157/jenkins/job/%E3%82%A4%E3%82%B7%20(EC)/job/Automation/job/Update/job/EC%20Service%20Update%20Step%201%20(CF3)/) [Blue-Green Upgrade Step2](http://10.227.87.157/jenkins/job/%E3%82%A4%E3%82%B7%20(EC)/job/Automation/job/Update/job/EC%20Service%20Update%20Step%202%20(CF3)/)
Predix East | v1 | [hokkaido.198](https://github.build.ge.com/Enterprise-Connect/ec-sdk/archive/v1.hokkaido.198.zip) | yokohama.1148 [project access](https://github.build.ge.com/Enterprise-Connect/ec-service/tree/v1.yokohama.1148) | tamakura.10 [project acccess](https://github.build.ge.com/Enterprise-Connect/ec-predix-service-broker/tree/v1.tamakura.10) | [v1.hokkaido.198](https://github.build.ge.com/Enterprise-Connect/ec-sdk/tree/v1.hokkaido.198/plugins)
Predix Azure | v1 | TBD | TBD | TBD
Frankfurt PoP | v1 | TBD | TBD | TBD
GovCloud | TBD | TBD | TBD | TBD

## Live Data
Env | Gateway | Service | Test Status
--- | --- | --- | ---
Predix CF1 | <a href='https://ec-int-test-gateway.run.aws-usw02-dev.ice.predix.io/health'>Health</a> | [API](https://5600f64f-4d64-4af6-9bd1-0939d8880049.run.aws-usw02-pr.ice.predix.io/v1/index/)</a> | [Internal Build](http://10.227.87.157/jenkins/blue/organizations/jenkins/%E3%82%A4%E3%82%B7%20(EC)%2FAutomation%2FQA%2FEC%20Integration%20Test%20(CF1)/activity)
Predix CF3 | [Health](https://ec-int-test-gateway.run.aws-usw02-dev.ice.predix.io/health) | [API](https://e27fc834-28be-4851-9d6a-b7033d568270.run.aws-usw02-dev.ice.predix.io/v1beta/index/) | [Internal Build](http://10.227.87.157/jenkins/blue/organizations/jenkins/%E3%82%A4%E3%82%B7%20(EC)%2FAutomation%2FQA%2FEC%20Integration%20Test%20(CF3)/activity)
Predix East | [Health](https://ec-int-test-gateway.run.aws-usw02-dev.ice.predix.io/health) | [API](https://e27fc834-28be-4851-9d6a-b7033d568270.run.aws-usw02-dev.ice.predix.io/v1beta/index/) | [Internal Build](http://10.227.87.157/jenkins/blue/organizations/jenkins/%E3%82%A4%E3%82%B7%20(EC)%2FAutomation%2FQA%2FEC%20Integration%20Test%20(CF3)/activity)

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
 
### Configuration
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