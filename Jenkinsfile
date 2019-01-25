pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
