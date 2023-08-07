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
            //def dockerfilePath = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Jenkins Job_1'
            //def absoluteDockerfilePath = "${WORKSPACE}/${dockerfilePath}"
            //echo "Dockerfile Path: ${absoluteDockerfilePath}"
            //sh "docker login -u indhu1024 -p 'Indhu@2000' https://hub.docker.com/"
           
            //withDockerRegistry(url:'https://hub.docker.com/', credentialsId:'docker-hub-credentials'){
              
                 //sh "docker build -t indhu1024/my-docker-image:latest -f ${absoluteDockerfilePath} ."
                //sh "docker push indhu1024/my-docker-image:latest"
            //hi}
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

