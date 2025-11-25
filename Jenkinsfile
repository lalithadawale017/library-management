pipeline {
    agent any

    environment {
        JAVA_HOME = "C:/Users/DELL/.jdks/ms-17.0.16"
        PATH = "${JAVA_HOME}/bin;${env.PATH}"
        APP_PORT = "8082"
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
                echo "Stopping any old Spring Boot process on port ${APP_PORT}..."

                // Kill running process on port APP_PORT
                bat """
                @echo off
                set PORT=${APP_PORT}
                set FOUND=0
                for /F "tokens=5" %%a in ('netstat -aon ^| findstr :%PORT% ^| findstr LISTENING') do (
                    echo Killing old process %%a on port %PORT%
                    taskkill /PID %%a /F
                    set FOUND=1
                )
                if %FOUND%==0 (
                    echo No process found on port %PORT%, continuing...
                )
                """

                echo 'Starting new Spring Boot application...'

                // NOTE: running from inside workspace of Jenkins service
                bat """
                cd %WORKSPACE%
                start "" java -jar target\\library-management-0.0.1-SNAPSHOT.jar --server.port=${APP_PORT}
                """
            }
        }
    }
}
