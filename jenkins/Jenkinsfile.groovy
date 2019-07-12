node('master') {
  stage('Clone repo') {
    git 'https://github.com/SharifAbdulcoder/Docker-python.git'
  }
   stage("Docker build") {
     dir("${WORKSPACE}/dp") {
       sh "docker build -t sharifabdulcoder/app ."
     }
   }

   stage('Docker push') {
           dir("${WORKSPACE}") {
             sh "docker build -t http-server ."
           }
           dir("${WORKSPACE}/dp/") {
             sh "docker tag http-server:latest 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
      }
           dir("${WORKSPACE}/dp/") {
             sh "docker push 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
     }
  }

    stage('Terraform Destoy') {
           dir("${WORKSPACE}/dp") {
             sh "docker run -dti -p 80:8080 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
           }
         }
}



//
//
// node('master') {
//   git 'https://github.com/SharifAbdulcoder/Docker-python.git'
//   }
//
//
//   stage('Build Docker') {
//   if (!fileExists("Docker-python/Dockerfile")) {
//          error('Dockerfile missing.')
//        }
//     }
//
//
//   stage('Build Docker') {
//     dir("${WORKSPACE}/Docker-python") {
//       sh "docker build -t SharifAbdulcoder/app"
//       }
//     }
//
//    stage('Build Docker') {
//      dir("${WORKSPACE}/Docker-python") {
//        sh "docker run -dti -p 80:8080 SharifAbdulcoder/app"
//      }
//   }
