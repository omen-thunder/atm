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
      steps {
        script {
          echo 'Attempting to run tests'
          sh './gradlew check'
        }

    stage('Deploy') {
      steps {
        script {
          if (env.BRANCH_NAME.startsWith('master')) {
            sh(script: 'curl $BUILD_WEBHOOK_MASTER | grep -o OK')
          } else if (env.BRANCH_NAME.startsWith('staging')) {
            sh(script: 'curl $BUILD_WEBHOOK_STAGING | grep -o OK')
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

    always {
      junit '**/build/test-results/test/*.xml'
    }

  }
}