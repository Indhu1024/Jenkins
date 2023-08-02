// Jenkinsfile for CI/CD Pipeline
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the Jenkins repository
                git 'https://github.com/your-username/Jenkins.git'
            }
        }

        stage('Docker Build') {
            steps {
                // Build the Docker image
                script {
                    docker.withRegistry('https://your-docker-registry', 'docker-credentials') {
                        def customImage = docker.build('your-docker-registry/your-docker-image:latest', './path/to/dockerfile')
                        customImage.push()
                    }
                }
            }
        }

        stage('Trigger Second Job') {
            steps {
                // Trigger the second Jenkins job (provide the job name you created in Jenkins)
                build job: 'SecondJob'
            }
        }
    }
}
