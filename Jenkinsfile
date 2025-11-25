pipeline {
    agent any

    environment {
        JAVA_HOME = "C:/Users/DELL/.jdks/ms-17.0.16"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Run') {
            steps {
                // Run Spring Boot JAR in background
                bat 'start cmd /c java -jar target/library-management-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
