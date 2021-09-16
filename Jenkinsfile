pipeline {
  agent any

  tools {
    jdk 'openjdk-16'
  }
  environment {
    BUILD_WEBHOOK_STAGING = credentials('deploy-webhook-staging')
    BUILD_WEBHOOK_MASTER = credentials('deploy-webhook-master')
  }

  stages {
    stage('Clean') {
      steps {
        script {
          echo 'Attempting to clean the project'
          sh 'chmod +x gradlew'
          sh './gradlew clean'
        }

      }
    }

    stage('Build') {
      steps {
        script {
          echo 'Attempting to run build'
          sh './gradlew build -x test'
        }
      }
    }

    stage('Test') {
      agent {
        docker {
          image 'postgres:13-alpine'
          args '-e "POSTGRES_DB=kingsatm" -e "POSTGRES_USER=client" -e "POSTGRES_PASSWORD=client"'
        }
      }
      steps {
        dir('~/') {
          writeFile(file: '.hosts', text: '127.0.0.1 db')
        }
        script {
          echo 'Attempting to run tests'
          sh 'while pg_isready -d kingsatm -h localhost -p 5432 -U client; do sleep 1; done'
          sh './gradlew test'
          sh './gradlew check'
          junit '**/build/test-results/**/*.xml'
        }
      }
    }

    stage('Deploy') {
      steps {
        script {
          if (env.BRANCH_NAME.startsWith('master')) {
            sh 'curl "$BUILD_WEBHOOK_MASTER" | grep -o OK'
          } else if (env.BRANCH_NAME.startsWith('staging')) {
            sh 'curl "$BUILD_WEBHOOK_STAGING" | grep -o OK'
          }
        }
      }
    }

  }

  post {
    success {
      script {
        if (env.BRANCH_NAME.startsWith('PR')) {

          withCredentials([usernamePassword(credentialsId: 'github-userpass', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
            sh '''
curl "https://${GIT_USERNAME}:${GIT_PASSWORD}@api.github.sydney.edu.au/repos/SOFT2412-2021S2/R10_C11_G2_ATM/statuses/$GIT_COMMIT" \\
-H "Content-Type: application/json" \\
-X POST \\
-d "{\\"state\\": \\"success\\",\\"context\\": \\"continuous-integration/jenkins\\", \\"description\\": \\"Jenkins\\", \\"target_url\\": \\"https://kingsatm-jenkins.codepass.dev/job/KingsATM/$BUILD_NUMBER/console\\"}"
'''
          }
        }
      }

    }

    failure {
      script {
        if (env.BRANCH_NAME.startsWith('PR')) {

          withCredentials([usernamePassword(credentialsId: 'github-userpass', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
            sh '''
curl "https://${GIT_USERNAME}:${GIT_PASSWORD}@api.github.sydney.edu.au/repos/SOFT2412-2021S2/R10_C11_G2_ATM/statuses/$GIT_COMMIT" \\
-H "Content-Type: application/json" \\
-X POST \\
-d "{\\"state\\": \\"failure\\",\\"context\\": \\"continuous-integration/jenkins\\", \\"description\\": \\"Jenkins\\", \\"target_url\\": \\"https://kingsatm-jenkins.codepass.dev/job/KingsATM/$BUILD_NUMBER/console\\"}"
'''
          }
        }
      }

    }


  }

}