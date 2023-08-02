// Jenkinsfile for CI/CD Pipeline
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the Jenkins repository
                git 'https://github.com/Indhu1024/Jenkins.git'
            }
        }

        stage('Docker Build') {
            steps {
                // Build the Docker image
                script {
                    docker.withRegistry('https://hub.docker.com/repositories/indhu1024', 'docker-hub-credentials') {
                        def customImage = docker.build('indhu1024/my-docker-image:latest', 'https://github.com/Indhu1024/Jenkins.git')
                        customImage.push()
                    }
                }
            }
        }

        stage('Trigger Second Job') {
            steps {
                // Trigger the second Jenkins job (provide the job name you created in Jenkins)
                build job: 'Jenkins Job_2'
            }
        }
    }
}
