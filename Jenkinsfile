pipeline {
    agent any

    environment {
        APP_PORT = '8081'
        JAR_FILE = 'target\\library-management-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                git branch: 'main', url: 'https://github.com/lalithadawale017/library-management.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project with Maven...'
                bat 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                bat """
                :: Stop old application if running
                for /f "tokens=5" %%a in ('netstat -aon ^| findstr :%APP_PORT% ^| findstr LISTENING') do (
                    echo Killing old process %%a on port %APP_PORT%
                    taskkill /PID %%a /F
                )

                :: Start new application
                echo Starting new Spring Boot application...
                start "" java -jar %JAR_FILE%
                """
            }
        }
    }

    post {
        success {
            echo 'Build and deployment finished successfully!'
        }
        failure {
            echo 'Something went wrong. Check the logs.'
        }
    }
}
