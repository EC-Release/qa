# -*- coding: utf-8 -*-
__author__ = '安田 アポロ <apolo.yasuda@ge.com>'

'''
   this project is designed to conduct the benchmark test for EC
   based on the model postgres<-server->gateway<-client<-request
'''

import sys
print sys.path

import  os, json, yaml, base64, sys,  threading, atexit
from time import sleep

#import ec_common
from ec_common import Common
from ec_test_postgres import PostgresTest

c=Common(__name__)

CF_API_URI=os.environ['CF_API_URI']
CF_USERNAME=os.environ['CF_USERNAME'] 
CF_PASSWORD=os.environ['CF_PASSWORD']
CF_ORG=os.environ['CF_ORG']
CF_SPC=os.environ['CF_SPC']
EC_SDK_DOWNLOAD=os.environ['EC_SDK_DOWNLOAD']
EC_SHARED_DOMAIN=os.environ['EC_SHARED_DOMAIN']
EC_SERVICE=os.environ['EC_SERVICE']
EC_SERVICE_TKN=os.environ['EC_SERVICE_TKN']
EC_SERVICE_ZONE=os.environ['EC_SERVICE_ZONE']
EC_CLIENT_ID=os.environ['EC_CLIENT_ID']
EC_SERVER_ID=os.environ['EC_SERVER_ID']
EC_GROUP_ID=os.environ['EC_GROUP_ID']
EC_UAA=os.environ['EC_UAA']
EC_UAA_CID=os.environ['EC_UAA_CID']
EC_UAA_CSC=os.environ['EC_UAA_CSC']
EC_PSQL_HOST=os.environ['EC_PSQL_HOST']

EC_TEST_LOOP=int(os.environ['EC_TEST_LOOP'])
EC_TEST_ROW=os.environ['EC_TEST_ROW']
EC_TEST_THREAD=int(os.environ['EC_TEST_THREAD'])
EC_TEST_BREAK=os.environ['EC_TEST_BREAK']

#PROXY=os.environ['https_proxy']

PG_CONNSTR=os.environ['PG_CONNSTR']

PG_SERVICE_INST=os.environ['PG_SERVICE_INST']
EC_SERVICE_INST=os.environ['EC_SERVICE_INST']

EC_SDK_DIR='sdk'
EC_ART_NAME='ecagent_linux_var'
EC_GATEWAY_APP_NAME=os.environ['EC_GATEWAY_INST']
EC_AGT_GATEWAY_VAR='./'+EC_ART_NAME+' -mod gateway -lpt 7990 -zon '+EC_SERVICE_ZONE+' -sst '+EC_SERVICE+' -dbg -tkn '+EC_SERVICE_TKN+' -shc &'

EC_SERVER_APP_NAME=os.environ['EC_SERVER_INST']
EC_AGT_SERVER_VAR='./'+EC_ART_NAME+' -mod server -aid '+EC_SERVER_ID+' -hst ws://localhost:7990/agent -rht localhost -rpt 5432 -cid '+EC_UAA_CID+' -csc '+EC_UAA_CSC+' -oa2 '+EC_UAA+' -dur 300 -dbg -hca 8990 -shc -zon '+EC_SERVICE_ZONE+' -sst '+EC_SERVICE+' -grp '+EC_GROUP_ID+' &' 

#noproxy env
EC_AGT_CLIENT_VAR='./'+EC_ART_NAME+' -mod client -aid '+EC_CLIENT_ID+' -hst ws://localhost:7990/agent -lpt 17990 -tid '+EC_SERVER_ID+' -oa2 '+EC_UAA+' -cid '+EC_UAA_CID+' -csc '+EC_UAA_CSC+' -shc -dur 300 -dbg -grp '+EC_GROUP_ID+' &'

#EC_AGT_CLIENT_VAR='./'+EC_ART_NAME+' -mod client -aid '+EC_CLIENT_ID+' -hst wss://'+EC_GATEWAY_APP_NAME+'.'+EC_SHARED_DOMAIN+'/agent -lpt 7990 -tid '+EC_SERVER_ID+' -oa2 '+EC_UAA+' -cid '+EC_UAA_CID+' -csc '+EC_UAA_CSC+' -shc -dur 300 -dbg -pxy '+PROXY+' &'

EC_YML='manifest.yml'
EC_BATCH='ec.sh'
CF_LOGIN_CMD='cf login -a {} -u {} -p {} -o "{}" -s "{}"'.format(CF_API_URI,CF_USERNAME,CF_PASSWORD,CF_ORG,CF_SPC)
CF_PUSH_CMD='cf push'
EC_CHMOD_AGT_CMD='chmod 755 ./'+EC_ART_NAME

EC_SCALE_NUM=os.environ['EC_SCALE_NUM']
EC_SCALE_CMD='cf scale {} -i {}'

EC_TEST_TABLE='ectest'

EC_TEST_INDEX=os.environ['EC_TEST_INDEX']

def init():
    c.logger.info('begin deploy.')
  
    os.system('chmod 777 {} && rm -Rf {} && ls -al'.format(EC_SDK_DIR,EC_SDK_DIR))
    c.Download(EC_SDK_DOWNLOAD)

    c.Unzip(EC_SDK_DOWNLOAD[EC_SDK_DOWNLOAD.rfind('/')+1:],EC_SDK_DIR)
    EC_ARCH_NAME=EC_ART_NAME+'.tar.gz'
    arch=c.find(EC_ARCH_NAME,EC_SDK_DIR)
    os.system('tar -C {} -xvzf {}'.format(EC_SDK_DIR,arch))
    art=c.find(EC_ART_NAME,EC_SDK_DIR)
    #os.system('ls -la {} && rm ./{}'.format(art,EC_ART_NAME))
    
    #keep the original
    from shutil import copyfile
    copyfile(art, './'+EC_ART_NAME)
    os.system('ls -la && chmod 777 ./{} && ./{} -ver'.format(EC_ART_NAME,EC_ART_NAME))

    os.system(EC_AGT_GATEWAY_VAR)
    sleep(7)

    os.system(EC_AGT_SERVER_VAR)
    sleep(7)

    os.system(EC_AGT_CLIENT_VAR)
    sleep(7)
    
def start():
    #launch agent in client mode
    os.system(EC_AGT_CLIENT_VAR)

    print('client agent cmd-')
    print(EC_AGT_CLIENT_VAR)
    
    sleep(5)
    
    db=PostgresTest(PG_CONNSTR)

    #local test
    #db=PostgresTest("host='localhost' port='5432' dbname='postgres' user='postgres' password='sa'")
    db.initTestData(EC_TEST_TABLE)
    db.conn.close()
    
    atexit.register(exit_handler)

    threads=[]
    for i in range(1,EC_TEST_THREAD):
        t = threading.Thread(target=TestMain, args=(EC_TEST_TABLE,PG_CONNSTR,i,))
        #local test
        #t = threading.Thread(target=TestMain, args=(EC_TEST_TABLE,"host='localhost' port='5432' dbname='postgres' user='postgres' password='sa'",i,))
        threads.append(t)
        t.start()

def cleanup():
    os.system('chmod -R 777 ./;rm {};rm -Rf {};ls -al'.format(EC_SDK_DOWNLOAD,EC_SDK_DIR))
    
    
def main():
    
    if EC_TEST_INDEX=='start':
        start()
        return
    
    init()

    #wait till servers list got cleared out
    sleep(20)

    start()

    
def TestMain(tname,connstr,tnum):
    #db=PostgresTest(connstr)
    for x in range(0,EC_TEST_LOOP):
        db=PostgresTest(connstr)
        if db.conn==None:
            print('postgres server failed to accept connection. try again.')
            continue
            
        ref2=db.query('select * from '+tname+' limit '+EC_TEST_ROW+';',False)
        ref2['threadNum']=tnum
        print(ref2)
        print('query hibernated for ' +EC_TEST_BREAK+' seconds.')
        sleep(int(EC_TEST_BREAK))
        db.conn.close()
        
def exit_handler():
    cleanup()
    #minimise the server resource
    os.system(EC_SCALE_CMD.format(EC_SERVER_APP_NAME, 1))
        
if __name__=='__main__':
    main()
