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

        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                url: 'https://github.com/SandhiyaUmapathi/expense-tracker.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package Application') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Check Docker') {
            steps {
                bat 'docker info'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat '''
                docker build -t %DOCKER_IMAGE%:latest .
                docker build -t %DOCKER_IMAGE%:%IMAGE_TAG% .
                '''
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat '''
                    docker logout

                    echo Logging in as %DOCKER_USER%

                    docker login -u %DOCKER_USER% -p %DOCKER_PASS%

                    IF %ERRORLEVEL% NEQ 0 (
                        echo Docker login failed
                        exit /b 1
                    )

                    docker push %DOCKER_IMAGE%:latest
                    docker push %DOCKER_IMAGE%:%IMAGE_TAG%
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat '''
                echo Setting KUBECONFIG...
                set KUBECONFIG=C:\\Users\\sandh\\.kube\\config

                echo Checking cluster...
                kubectl get nodes

                echo Deploying application...
                kubectl apply -f deployment.yaml --validate=false
                '''
            }
        }
    }

    post {
        success {
            echo 'Expense Tracker App deployed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
    }
}
