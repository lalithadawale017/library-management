pipeline {
    agent any

    environment {
        JAVA_HOME = "C:/Users/DELL/.jdks/ms-17.0.16"
        PATH = "${JAVA_HOME}/bin;${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Stopping any old Spring Boot process on port 8081...'
                // Kill any process listening on 8081
                bat '''
                for /F "tokens=5" %%a in ('netstat -aon ^| findstr :8081 ^| findstr LISTENING') do (
                    echo Killing old process %%a on port 8081
                    taskkill /PID %%a /F
                )
                '''

                echo 'Starting new Spring Boot application on port 8081...'
                // Start the app in background
                bat 'start "" java -jar target\\library-management-0.0.1-SNAPSHOT.jar --server.port=8081'
            }
        }
    }
}
