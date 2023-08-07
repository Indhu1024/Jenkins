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
        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]){
        script {
            def dockerRegistryCredentials = [
           "username": env.DOCKER_HUB_USERNAME,
           "password": env.DOCKER_HUB_PASSWORD
          ]

                    docker.withRegistry('https://docker.io', 'docker-hub-credentials') {
                        docker.image('my-docker-image:newtag').build()
                        docker.image('my-docker-image:newtag').push()
                    }
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

