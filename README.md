# Agent Validations Spec

### Goal
- Agents usage and validated functionality in multiple scenarios

## Revision Matrix
Rev. (prefix) | Download/Release | SDK/Plugins
--- | --- | --- 
v1 | [hokkaido.212](https://github.com/EC-Release/sdk/tree/v1.hokkaido.212/dist)<br /><sup>[Release Note](https://github.com/EC-Release/sdk/releases/tag/v1.hokkaido.212) </sup> | [v1.hokkaido.212](https://github.com/EC-Release/sdk/tree/v1.hokkaido.212/plugins)  
v1beta | [v1beta.fukuoka.1726](https://github.com/EC-Release/sdk/tree/v1beta.fukuoka.1726/dist)<br /><sup>[Release Note](https://github.com/EC-Release/sdk/releases/tag/v1beta.fukuoka.1726)</sup> | [v1beta.fukuoka.1726](https://github.com/EC-Release/sdk/tree/v1beta.fukuoka.1726/plugins)  

### v1beta.fukuoka.1726
#### Scenario: Traditional Connection with CF gateway

- Deploy gateway in cf

```shell script
./agent -mod gateway -gpt ${PORT} \
 -zon {ec-service-zone-id in guid format} \
 -sst {ec-service-uri ending with predix.io} \
 -tkn {ec-service-admin-token encrypted string} \
 -hst {ec-gateway-uri} \
 -dbg
```
- Server script

```shell script
./agent -mod server -aid {server-agent-id} \
-grp {ec-service-group} \
-cid {uaa-client-id} -csc {uaa-client-secret} -dur {token-validity-period in number} \
-oa2 {uaa-service-url ending with /oauth/token} \
-hst wss://{ec-gateway-uri}/agent \
-sst {ec-service-uri ending with predix.io} \
-zon {ec-service-zone-id in guid format} \
-rht {target-system-host/ip} -rpt {target-system-port} -dbg 
```

- Client script

```shell script
./agent -mod client -aid {client-agent-id} -tid {server-agent-id} \
-zon {ec-service-zone-id in guid format} -grp {ec-service-group} \
-hst wss://{ec-gateway-uri}/agent \
-cid {uaa-client-id} -csc {uaa-client-secret} \
-oa2 {uaa-service-url ending with /oauth/token} \
-dur {token-validity-period in number} -lpt {ec-client-listening-port} -dbg
```

#### Scenario: Fusemode - Gwserver

- ```gwserver``` script

Add gateway host to ```no_proxy```
 
```shell script
export http_proxy={corp-proxy-url}
export https_proxy={corp-proxy-url}
export HTTP_PROXY={corp-proxy-url}
export HTTPS_PROXY={corp-proxy-url}
export no_proxy={gateway-host}

./agent -mod gw:server -gpt {gateway-port} -aid {server-agent-id} \
-zon {ec-service-zone-id in guid format} -grp {ec-service-group} \
-sst {ec-service-uri ending with predix.io} -tkn {ec-service-admin-token encrypted string} \
-hst ws://{gateway-host}:{gateway-port}/agent \
-cid {uaa-client-id} -csc {uaa-client-secret} \
-oa2 {uaa-service-url ending with /oauth/token} \
-dur {token-validity-period in number} \
-rht {target-system-host/ip} -rpt {target-system-port} -dbg
```

- ```client``` script

Add gateway host to ```no_proxy```, if connection to gateway should not go through corp proxy
```shell script
export http_proxy={corp-proxy-url}
export https_proxy={corp-proxy-url}
export HTTP_PROXY={corp-proxy-url}
export HTTPS_PROXY={corp-proxy-url}
export no_proxy={gateway-host}

./agent -mod client -aid {client-agent-id} -tid {server-agent-id} \
-zon {ec-service-zone-id in guid format} -grp {ec-service-group} \
-hst ws://{gateway-host}:{gateway-port}/agent \
-cid {uaa-client-id} -csc {uaa-client-secret} \
-oa2 {uaa-service-url ending with /oauth/token} \
-dur {token-validity-period in number} -lpt {ec-client-listening-port} -dbg
```
#### Scenario: Fusemode - Gwclient

- ```gwclient``` script
```shell script
export http_proxy={corp-proxy-url}
export https_proxy={corp-proxy-url}
export HTTP_PROXY={corp-proxy-url}
export HTTPS_PROXY={corp-proxy-url}
export no_proxy={gateway-host}

./agent -mod gw:client -gpt {gateway-port} \
-zon {ec-service-zone-id in guid format} \
-sst {ec-service-uri ending with predix.io} \
-tkn {ec-service-admin-token encrypted string} \
-hst ws://{gateway-host}:{gateway-port}/agent \
-aid {client-agent-id} -tid {server-agent-id} \
-grp {ec-service-group} -cid {uaa-client-id} -csc {uaa-client-secret} \
-dur {token-validity-period in number} \
-oa2 {uaa-service-url ending with /oauth/token} \
-lpt {ec-client-listening-port} -dbg
```

- ```server``` script
```shell script
export http_proxy={corp-proxy-url}
export https_proxy={corp-proxy-url}
export HTTP_PROXY={corp-proxy-url}
export HTTPS_PROXY={corp-proxy-url}
export no_proxy={gateway-host}

./agent -mod server -aid {server-agent-id} \
-grp {ec-service-group} \
-cid {uaa-client-id} -csc {uaa-client-secret} -dur {token-validity-period in number} \
-oa2 {uaa-service-url ending with /oauth/token} \
-hst ws://{gateway-host}:{gateway-port}/agent \
-sst {ec-service-uri ending with predix.io} \
-zon {ec-service-zone-id in guid format} \
-rht {target-system-host/ip} -rpt {target-system-port} -dbg
```

#### Scenario: VLAN

- ```server``` script
```shell script
./agent -mod server -aid {server-agent-id} \
-grp {ec-service-group} \
-cid {uaa-client-id} -csc {uaa-client-secret} -dur {token-validity-period in number} \
-oa2 {uaa-service-url ending with /oauth/token} \
-hst ws://{gateway-host}:{gateway-port}/agent \
-sst {ec-service-uri ending with predix.io} \
-zon {ec-service-zone-id in guid format} \
-rht {target-system-host/ip} -rpt {target-system-port} -dbg  
```

- ```client``` script
```shell script
./ecagent -mod client \
-aid {client-agent-id} -tid {server-agent-id} \
-grp {ec-service-group} \
-cid {uaa-client-id} -csc {uaa-client-secret} -dur {token-validity-period in number} \
-oa2 {uaa-service-url ending with /oauth/token} \
-hst ws://{gateway-host}:{gateway-port}/agent \
-lpt {ec-client-listening-port} \
-rpt {target-system-port} \
-dbg -plg -vln
```

- plugins.yml at client side

```yaml
--- 
ec-plugin: 
  vlan: 
    command: ./vlan
    ips: "{Target system IP's or CIDR range}"
    status: active
```

- Client must run in sudo mode

#### Scenario: TLS

In Progress

