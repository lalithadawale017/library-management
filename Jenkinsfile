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

        bat """
        @echo off
        set PORT=${APP_PORT}

        echo Checking for process on port %PORT%...

        for /F "tokens=5" %%p in ('netstat -ano ^| findstr :%PORT% ^| findstr LISTENING') do (
            echo Killing process %%p on port %PORT%...
            taskkill /F /PID %%p >nul 2>&1
        )

        echo Process check complete.
        exit /B 0
        """

        echo "Starting Spring Boot application..."

        bat """
        @echo off
        cd "%WORKSPACE%"
        echo Starting app from %WORKSPACE%

        start "LibraryApp" cmd /c "java -jar target\\library-management-0.0.1-SNAPSHOT.jar --server.port=${APP_PORT} > app.log 2>&1"

        exit /B 0
        """
    }
  }
 }
}
