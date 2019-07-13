node('master') {
  stage('Clone repo') {
    git 'https://github.com/SharifAbdulcoder/Docker-python.git'
  }

   stage('Docker build & push') {
           dir("${WORKSPACE}") {
             sh "docker build -t http-server ."
           }
           dir("${WORKSPACE}") {
             sh "docker tag http-server:latest 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
      }
           dir("${WORKSPACE}") {
             // Renewing or Creating Authorization Token to AWS ECR
             sh "'$(aws ecr get-login --no-include-email)'"
             sh "docker push 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
     }
  }
    stage('Docker run') {
           dir("${WORKSPACE}") {
             sh "docker run -dti -p 81:8080 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
           }
         }
}


// ########## something to use ##############
//
// stage("Docker") {
//   dir(path) {
//     docker.build("my-image:latest")
//   }
//   docker.withRegistry("https://<my-aws-id>.dkr.ecr.eu-central-1.amazonaws.com", "ecr:eu-central-1:aws-jenkins") {
//     // debug
//     sh "cat /root/.dockercfg"
//     docker.image("my-image:latest").push()
//   }
// }
