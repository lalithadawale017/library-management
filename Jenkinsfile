pipeline {
    agent any

    // Tools configured in Jenkins -> Manage Jenkins -> Global Tool Configuration
    tools {
        maven 'maven3'   // Name of Maven installation in Jenkins
        jdk 'jdk17'      // Name of JDK installation in Jenkins
    }

    environment {
        // Optional: set environment variables
        MVN_HOME = "${tool 'maven3'}"
        JAVA_HOME = "${tool 'jdk17'}"
        PATH = "${env.MVN_HOME}\\bin;${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Running Maven Build...'
                // Windows uses 'bat' instead of 'sh'
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running Unit Tests...'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging project...'
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}