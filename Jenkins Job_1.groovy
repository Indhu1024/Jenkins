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
                     withDockerRegistry(url:'https://hub.docker.com/', credentialsId:'docker-hub-credentials') {
                         def dockerLoginCommand = "docker login -u indhu1024 --password-stdin https://hub.docker.com/"
                          def dockerLoginProcess = dockerLoginCommand.execute()
                         dockerLoginProcess.inputStream.withWriter { writer ->
                         writer.write('Indhu@2000')
    }
                          dockerLoginProcess.waitFor()
            if (dockerLoginProcess.exitValue() != 0) {
              error("Docker login failed: ${dockerLoginProcess.err.text}")
    }
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
