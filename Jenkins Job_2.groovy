// Jenkinsfile for SecondJob
pipeline {
    agent any

    stages {
        stage('Pull Docker Image') {
            steps {
                // Pull the Docker image
                script {
                    docker.withRegistry('https://hub.docker.com', 'docker-hub-credentials') {
                        docker.image('https://hub.docker.com/my-docker-image:latest').pull()
                    }
                }
            }
        }

        stage('Run Container') {
            steps {
                // Run the container and print the file changes
                script {
                    docker.image('https://hub.docker.com/my-docker-image:latest').inside {
                        sh 'cat Sample.txt'
                    }
                }
            }
        }
    }
}

