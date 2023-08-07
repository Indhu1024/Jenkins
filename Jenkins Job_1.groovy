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
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image('my-docker-image:newtag').build()
                    }
            #def dockerfilePath = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Jenkins Job_1'
            #def absoluteDockerfilePath = "${WORKSPACE}/${dockerfilePath}"
            #echo "Dockerfile Path: ${absoluteDockerfilePath}"
            #sh "docker login -u indhu1024 -p 'Indhu@2000' https://hub.docker.com/"
           
            #withDockerRegistry(url:'https://hub.docker.com/', credentialsId:'docker-hub-credentials'){
              
                 #sh "docker build -t indhu1024/my-docker-image:latest -f ${absoluteDockerfilePath} ."
                #sh "docker push indhu1024/my-docker-image:latest"
            #hi}
        }
    }
}
        stage('Push Docker Image to Docker Hub') {
            steps {
                // Push the built Docker image to Docker Hub
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image('my-docker-image:newtag').push()
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
