#!groovy
/*
 * project ec connectivity test
 * author: chia.chang@ge.com
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
//git checkout develop
	 sh """ 
chmod 755 ./ec_init.sh
./ec_init.sh
"""
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