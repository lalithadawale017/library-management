pipeline {
    agent any

    environment {
        JAVA_HOME = "C:/Users/DELL/.jdks/ms-17.0.16"
        PATH = "${JAVA_HOME}/bin;${env.PATH}" // Windows uses ; as separator
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Stopping any old Spring Boot process on port 8081 (if exists)...'
                // This will try to kill old process but won't fail if nothing found
                bat '''
                @echo off
                set PORT=8081
                for /F "tokens=5" %%a in ('netstat -aon ^| findstr :%PORT% ^| findstr LISTENING') do (
                    echo Killing old process %%a on port %PORT%
                    taskkill /PID %%a /F || echo "No process found, continuing..."
                )
                '''

                echo 'Starting new Spring Boot application...'
                // Start Spring Boot in background
                bat 'start "" java -jar target\\library-management-0.0.1-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            echo 'Build and deployment finished successfully!'
        }
        failure {
            echo 'Pipeline failed. Check console output for errors.'
        }
    }
}
