node('master') {
    git 'https://github.com/SharifAbdulcoder/Docker-python.git'
  }

   stage("Docker Build") {
     dir("${WORKSPACE}/Docker_pipeline") {
       sh "docker build -t sharifabdulcoder/app . "
     }
   }

   stage('Push') {
           dir("${WORKSPACE}/Docker_pipeline") {
             echo "##### Pushing image to Amazon ECR ####"
             sh "docker tag http-server:latest 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
             sh "docker push 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
           }
         }

  // stage('Terraform Destoy') {
  //        dir("${WORKSPACE}/Docker_pipeline") {
  //          echo "##### Terraform Destroying the Changes ####"
  //          sh "terraform destroy  --auto-approve"
  //        }
  //      }
