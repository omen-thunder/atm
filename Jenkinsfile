pipeline {
  agent any

  environment {
    BUILD_WEBHOOK_STAGING = credentials('deploy-webhook-staging')
    BUILD_WEBHOOK_MASTER = credentials('deploy-webhook-master')
  }

  stages {

    stage('System Check') {
      steps {
        script {

          def gradle = docker.image('gradle:7-jdk16')
          gradle.pull()
          gradle.inside("--entrypoint=''") {
            sh 'gradle -v'
          }

        }
      }
    }

    stage('Clean') {
      steps {
        script {
          echo 'Attempting to clean the project'
          sh 'gradle clean'
        }
      }
    }

    stage('Build') {
      steps {
        script {
          echo 'Attempting to run build'
          sh 'gradle build -x test'
        }
      }
    }

    stage('Test') {
      steps {
        script {
          echo 'Starting Postgres container'

          docker.image('postgres:13-alpine').withRun('-p 5432:5432 -e "POSTGRES_DB=kingsatm" -e "POSTGRES_USER=client" -e "POSTGRES_PASSWORD=client"'){ c -> 

            echo 'Attempting to run tests'
            sh 'gradle test --stacktrace --args="--spring.datasource.url=jdbc:postgresql://db:5432/kingsatm"'
            junit '**/build/test-results/**/*.xml'
            
          }
        }
      }
    }

    stage('Check') {
      steps {
        echo 'Attempting to run checks'
        sh './gradlew check'
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
