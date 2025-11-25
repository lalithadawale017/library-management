pipeline {
    agent any

    environment {
        JAVA_HOME = "C:/Users/DELL/.jdks/ms-17.0.16"
        PATH = "${JAVA_HOME}/bin;${env.PATH}" // Use ; for Windows path separator
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests' // Skip tests for faster builds, optional
            }
        }
        stage('Run') {
            steps {
                // Optional: stop previous running instance to avoid port conflicts
                bat 'taskkill /F /IM java.exe || echo No previous Java process'

                // Run Spring Boot JAR in background
                bat 'start cmd /c java -jar target\\library-management-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
