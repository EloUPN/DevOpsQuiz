pipeline {
  agent any
  options { timestamps(); disableConcurrentBuilds() }
  stages {
    stage('BUILD') { steps { sh 'chmod +x scripts/*.sh && ./scripts/build.sh' } }
    stage('TEST') { steps { sh './scripts/setup-junit.sh && ./scripts/test.sh' } }
    stage('RELEASE / DEPLOY') {
      when {
	    expression {
        	env.GIT_BRANCH == 'origin/main' ||
        	env.GIT_BRANCH == 'main'
    	    }
      }
      steps {
        sh '''
          set -eu
          docker build -t devopsquiz:${BUILD_NUMBER} -t devopsquiz:latest .
          docker image inspect devopsquiz:latest >/dev/null
          docker run --rm devopsquiz:latest --healthcheck | grep HEALTHCHECK_OK
          docker volume create devopsquiz-scores
          docker rm -f devopsquiz-prod >/dev/null 2>&1 || true
          docker create -it --name devopsquiz-prod -v devopsquiz-scores:/data devopsquiz:latest
          docker inspect devopsquiz-prod --format '{{.State.Status}}'
        '''
      }
    }
  }
  post { always { junit allowEmptyResults: true, testResults: 'build/test-results/TEST-*.xml' } }
}
