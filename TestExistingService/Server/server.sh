#!/usr/bin/env bash
./ecagent_windows_sys -mod server \
-aid gIMFph \
-grp default_y9tdWqEZoAuOFyS \
-cid uaaclient1-testexisting \
-csc uaaclient1secret \
-dur 3000 \
-oa2 https://d82d84cb-9e75-4713-8b22-4a4a036c7e1b.predix-uaa.run.aws-usw02-dev.ice.predix.io/oauth/token \
-hst wss://gateway-testexisting.run.aws-usw02-dev.ice.predix.io/agent \
-zon b04ed617-6db3-4faf-9009-a35e1c558892 \
-sst https://b04ed617-6db3-4faf-9009-a35e1c558892.run.aws-usw02-dev.ice.predix.io \
-rht localhost \
-rpt 5432 \
-hca 8081 \
-pxy http://PITC-Zscaler-ASPAC-Bangalore3PR.proxy.corporate.ge.com:80
