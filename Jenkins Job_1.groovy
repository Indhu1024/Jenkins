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
                     def dockerfilePath = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Jenkins Job_1\\Jenkins Job_1.groovy'
                     def absoluteDockerfilePath = "${WORKSPACE}/${dockerfilePath}"
                     echo "Dockerfile Path: ${absoluteDockerfilePath}"
                     docker.withRegistry('https://hub.docker.com/', 'docker-hub-credentials') {
                        def customImage = docker.build('indhu1024/my-docker-image:latest', "-f ${absoluteDockerfilePath} .")
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
