# aws gateway
## Enterprise-Connect test with direct connection
Execute the root build.sh will produce a docker image which is the foundation of the related test in the subdirectories, respectively. The artifact image will be checked-in the designated dtr repo in the end of the script.  

- Run the gateway in aws VPC

- Run the server agent in target network

- Run the client script in source network

- Run the db_main.py as below - 

```
-- Mysql
python db_main.py --database mysql --operation {operation} --host {clientIP} --port {lpt} --user {dbuser} --password {dbpassword} --dbname {databasename}

-- Postgres
python db_main.py --database postgres --operation {operation} --host {clientIP} --port {lpt} --user {dbuser} --password {dbpassword} --dbname {databasename}

-- Execute below command to get help and allowed values for arguments
python db_main.py --help
```

## Performance statistics

Agent | Gateway | Environment | Time to complete process |
--- | --- | --- | --- |
v1.1beta.fukuoka.2725 | AWS | Gateway - US VPC, Server - On Prem, Client - EU VPC | 150s
