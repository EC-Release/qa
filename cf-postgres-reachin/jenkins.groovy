#!groovy
/*
 * project ec connectivity test
 * author: apolo.yasuda@ge.com
 */

node(env.JENV) {

    currentBuild.result = "SUCCESS"

    try {

	stage('init'){

	    checkout scm
	    sh 'ls -al'
	    echo "init"

	    cfc = env.CF_ENV_CRED

	    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: cfc,
			      usernameVariable: 'CF_USER', passwordVariable: 'CF_PSWD']]) {
		env.CF_USERNAME = sh (
		    script: "echo ${CF_USER}",
		    returnStdout: true
		).trim()
		env.CF_PASSWORD = sh (
		    script: "echo ${CF_PSWD}",
		    returnStdout: true
		).trim()
		//env.CF_USER = env.CF_USR
	    }
	}

	stage('docker build'){
	    dir(env.TEST_PATH) {
		sh """ 
eval "sed -i -e 's#{CF_API_URI}#${CF_API_URI}#g' ./env.list"
eval "sed -i -e 's#{CF_USERNAME}#${CF_USERNAME}#g' ./env.list"
eval "sed -i -e 's#{CF_PASSWORD}#${CF_PASSWORD}#g' ./env.list"
eval "sed -i -e 's#{CF_ORG}#${CF_ORG}#g' ./env.list"
eval "sed -i -e 's#{CF_SPC}#${CF_SPC}#g' ./env.list"
eval "sed -i -e 's#{EC_SDK_DOWNLOAD}#${EC_SDK_DOWNLOAD}#g' ./env.list"
eval "sed -i -e 's#{EC_SHARED_DOMAIN}#${EC_SHARED_DOMAIN}#g' ./env.list"
eval "sed -i -e 's#{EC_SERVICE}#${EC_SERVICE}#g' ./env.list"
eval "sed -i -e 's#{EC_SERVICE_TKN}#${EC_SERVICE_TKN}#g' ./env.list"
eval "sed -i -e 's#{EC_SERVICE_ZONE}#${EC_SERVICE_ZONE}#g' ./env.list"
eval "sed -i -e 's#{EC_UAA}#${EC_UAA}#g' ./env.list"
eval "sed -i -e 's#{EC_UAA_CID}#${EC_UAA_CID}#g' ./env.list"
eval "sed -i -e 's#{EC_UAA_CSC}#${EC_UAA_CSC}#g' ./env.list"
eval "sed -i -e 's#{EC_PSQL_HOST}#${EC_PSQL_HOST}#g' ./env.list"
eval "sed -i -e 's#{PG_CONNSTR}#${PG_CONNSTR}#g' ./env.list"
eval "sed -i -e 's#{PROXY}#${PROXY}#g' ./env.list"
eval "sed -i -e 's#{PG_SERVICE_INST}#${PG_SERVICE_INST}#g' ./env.list"
eval "sed -i -e 's#{EC_SERVICE_INST}#${EC_SERVICE_INST}#g' ./env.list"
eval "sed -i -e 's#{EC_CLIENT_ID}#${EC_CLIENT_ID}#g' ./env.list"
eval "sed -i -e 's#{EC_SERVER_ID}#${EC_SERVER_ID}#g' ./env.list"
eval "sed -i -e 's#{EC_TEST_BREAK}#${EC_TEST_BREAK}#g' ./env.list"

eval "sed -i -e 's#{EC_TEST_ROW}#${EC_TEST_ROW}#g' ./env.list"
eval "sed -i -e 's#{EC_TEST_LOOP}#${EC_TEST_LOOP}#g' ./env.list"
eval "sed -i -e 's#{EC_TEST_THREAD}#${EC_TEST_THREAD}#g' ./env.list"

eval "sed -i -e 's#{EC_SERVER_INST}#${EC_SERVER_INST}#g' ./env.list"
eval "sed -i -e 's#{EC_GATEWAY_INST}#${EC_GATEWAY_INST}#g' ./env.list"
eval "sed -i -e 's#{EC_SCALE_NUM}#${EC_SCALE_NUM}#g' ./env.list"
eval "sed -i -e 's#{EC_GROUP_ID}#${EC_GROUP_ID}#g' ./env.list"
cat ./env.list
"""

		withDockerRegistry([credentialsId: env.EC_DTR_CRED, url: env.EC_DTR_URL]) {

		    stage('pre-build'){

			echo "step to ensure the parent image is current."
			sh "docker pull dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta"
			
		    }
		    
		    docker.image('dtr.predix.io/dig-digiconnect/ec-agent-testsuite:v1beta').withRun('--env-file ./env.list -v "$(pwd)":/benchmark -u root') { c ->
			sh """
chmod -R 755 ./../
ls -al && pwd
python2 -u ./test.py
"""
		    }
		}
		
	    }
	    /* disable the file check-in until a better solution identified
	     sh """ 
	     git checkout staging
	     chmod 755 ./ec_init.sh
	     ./ec_init.sh | tee ./test-dump.log
	     sed -i '/Step 5 : ENV/d' test-dump.log
	     git add -f ./test-dump.log
	     git config user.email "ec.robot@ge.com"
	     git config user.name "EC Robot"
	     git commit -m "Test build# ${BUILD_ID} checked-in" 
	     git push origin staging
	     """
	     */
	    //_scm.GIT_COMMIT
	    
	}

	stage('cleanup'){

	    echo "clean up"
	    deleteDir()
	}

	stage('exit'){

	    echo "exit"
	}

    }
    catch (err) {

        currentBuild.result = "FAILURE"
	/* template for later usage
         mail body: "project build error is here: ${env.BUILD_URL}" ,
         from: 'xxxx@yyyy.com',
         replyTo: 'yyyy@yyyy.com',
         subject: 'project build failed',
         to: 'zzzz@yyyyy.com'
	 */
        deleteDir()
        throw err
    }

}
