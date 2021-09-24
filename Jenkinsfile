pipeline {
  agent any

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
      steps {
        script {
          echo '[ Starting Postgres container ]'

          docker.image('postgres:13-alpine').withRun('--expose 5432 -e "POSTGRES_DB=kingsatm" -e "POSTGRES_USER=client" -e "POSTGRES_PASSWORD=client"'){ c -> 
          
            docker.image('postgres:13-alpine').inside("--link ${c.id}:db"){
              echo '[ Waiting for Postgres to be ready ]'
              sh 'while ! pg_isready -d kingsatm -h db -p 5432 -U client; do sleep 1; done'
            }

            docker.image('gradle:7-jdk16').inside("--link ${c.id}:db"){
              echo '[ Attempting to run checks ]'
              sh 'gradle check -x npmBuild -D spring.datasource.url="jdbc:postgresql://db:5432/kingsatm"'

              echo '[ Saving JUnit Test Results ]'
              junit '**/build/test-results/**/*.xml'

              echo '[ Publish Jacoco Test Report ]'
              publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'backend/build/reports/jacoco/test/html', reportFiles: 'index.html', reportName: 'Jacoco Code Coverage Report', reportTitles: ''])
            }

          }
        }
      }
    }

    stage('Deploy') {
      steps {
        script {
          
          if (env.BRANCH_NAME.startsWith('master')) {
            echo '[ Trigger Production Deployment ]'
            sh 'curl "$BUILD_WEBHOOK_MASTER" | grep -o OK'
          } else if (env.BRANCH_NAME.startsWith('staging')) {
            echo '[ Trigger Staging Deployment ]'
            sh 'curl "$BUILD_WEBHOOK_STAGING" | grep -o OK'
          } else {
            echo '[ Skipped Deployment ]'
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
