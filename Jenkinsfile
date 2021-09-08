pipeline {
    agent any

    tools {
        jdk 'openjdk-16'
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
                    sh './gradlew build'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Attempting to run tests'
                    sh './gradlew check'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {

//                    if ("${env.BRANCH_NAME}" == "staging") {
//                        echo 'All tests passed, pushing staging branch to master branch'
//
//                        withCredentials([usernamePassword(credentialsId: '32ecfcb7-5392-4687-acfa-8ae8998b785c', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
//                            sh "git checkout master"
//                            sh 'git merge staging'
//                            sh 'git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.sydney.edu.au/SOFT2412-2021S2/gradle_tut3.git'
//                        }
//                    }

                    if (env.BRANCH_NAME == "master") {
                        echo 'Attempting to deploy to heroku'
                        sh 'git push heroku master'

                    }

                }

            }
        }
    }
    post {
        success {
            script {
                if (env.BRANCH_NAME.startsWith('PR')) {

                    withCredentials([usernamePassword(credentialsId: '32ecfcb7-5392-4687-acfa-8ae8998b785c', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                        sh '''
                            curl "https://${GIT_USERNAME}:${GIT_PASSWORD}@api.github.sydney.edu.au/repos/SOFT2412-2021S2/R10_C11_G2_ATM/statuses/$GIT_COMMIT" \\
                            -H "Content-Type: application/json" \\
                            -X POST \\
                            -d "{\\"state\\": \\"success\\",\\"context\\": \\"continuous-integration/jenkins\\", \\"description\\": \\"Jenkins\\", \\"target_url\\": \\"https://e77d-2001-8003-c4f3-7e00-a2cf-c5f9-42c2-791.ngrok.io/job/gradle_tut3/$BUILD_NUMBER/console\\"}"
                        '''
                    }
                }
            }
        }

        failure {
            script {
                if (env.BRANCH_NAME.startsWith('PR')) {

                    withCredentials([usernamePassword(credentialsId: '32ecfcb7-5392-4687-acfa-8ae8998b785c', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                        sh '''
                            curl "https://${GIT_USERNAME}:${GIT_PASSWORD}@api.github.sydney.edu.au/repos/SOFT2412-2021S2/R10_C11_G2_ATM/statuses/$GIT_COMMIT" \\
                            -H "Content-Type: application/json" \\
                            -X POST \\
                            -d "{\\"state\\": \\"failure\\",\\"context\\": \\"continuous-integration/jenkins\\", \\"description\\": \\"Jenkins\\", \\"target_url\\": \\"https://e77d-2001-8003-c4f3-7e00-a2cf-c5f9-42c2-791.ngrok.io/job/gradle_tut3/$BUILD_NUMBER/console\\"}"
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