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
                echo 'Building project...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo "Stopping application running on port ${APP_PORT}..."

                // Kill process running on port
                bat """
                @echo off
                set PORT=${APP_PORT}
                for /F "tokens=5" %%p in ('netstat -aon ^| findstr :%PORT% ^| findstr LISTENING') do (
                    echo Found process %%p on port %PORT%. Killing it...
                    taskkill /F /PID %%p
                )
                """

                echo "Starting Spring Boot application..."

                // Start application with logs
                bat """
                cd "%WORKSPACE%"
                echo Starting app from %WORKSPACE%
                start "LibraryApp" cmd /c "java -jar target\\library-management-0.0.1-SNAPSHOT.jar --server.port=${APP_PORT} > app.log 2>&1"
                """
            }
        }
    }
}
