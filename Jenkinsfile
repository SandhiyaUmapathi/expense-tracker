pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        DOCKER_IMAGE = "sandhiya1701/expense-tracker"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/SandhiyaUmapathi/expense-tracker.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                docker build -t %DOCKER_IMAGE%:latest .
                docker build -t %DOCKER_IMAGE%:%IMAGE_TAG% .
                '''
            }
        }

        stage('Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    bat '''
                    docker login -u %USER% -p %PASS%
                    docker push %DOCKER_IMAGE%:latest
                    docker push %DOCKER_IMAGE%:%IMAGE_TAG%
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                set KUBECONFIG=C:\\Users\\sandh\\.kube\\config
                kubectl apply -f deployment.yaml
                '''
            }
        }
    }
}