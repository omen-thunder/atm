pipeline {
  agent none

  environment {
    BUILD_WEBHOOK_STAGING = credentials('deploy-webhook-staging')
    BUILD_WEBHOOK_MASTER = credentials('deploy-webhook-master')
  }

  stages {

    stage('Build') {
      agent {
        docker {
          image 'gradle:7-jdk16'
          reuseNode true
        }
      }
      steps {
        script {
          echo '[ Check Gradle ]'
          sh 'gradle -v'

          echo '[ Attempting to clean the project ]'
          sh 'gradle clean'

          echo '[ Attempting to run build ]'
          sh 'gradle build -x test'
        }
      }
    }

    stage('Test') {
      agent any
      steps {
        script {
          echo '[ Starting Postgres container ]'

          docker.image('postgres:13-alpine').withRun('-p 5432:5432 -e "POSTGRES_DB=kingsatm" -e "POSTGRES_USER=client" -e "POSTGRES_PASSWORD=client"'){ c -> 

            docker.image('gradle:7-jdk16').inside {
              echo '[ Attempting to run tests ]'
              sh 'gradle test -Dspring.datasource.url=jdbc:postgresql://db:5432/kingsatm'

              echo '[ Attempting to run checks ]'
              sh 'gradle check'

              junit '**/build/test-results/**/*.xml'
            }

          }
        }
      }
    }

    stage('Deploy') {
      steps {
        script {
          echo '[ Trigger Deployment ]'
          
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
