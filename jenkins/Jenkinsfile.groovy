node('master') {
    git 'https://github.com/SharifAbdulcoder/Docker-python.git'
  }
   stage("Docker build") {
     dir("${WORKSPACE}/dp") {
       sh "echo 'hello' "
     }
   }

   stage('Terraform Apply/Plan') {
           dir("${WORKSPACE}/artemis_tf") {
             echo "##### Terraform Applying the Changes ####"
             sh "terraform apply  --auto-approve"
           }
           dir("${WORKSPACE}/artemis_tf/") {
             sh "terraform plan"
      }
    }

    stage('Terraform Destoy') {
           dir("${WORKSPACE}/artemis_tf") {
             sh "terraform destroy  --auto-approve"
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
