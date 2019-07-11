node('master') {
    git 'https://github.com/SharifAbdulcoder/Jenkins.git'
  }

   stage("Docker Build") {
     dir("${WORKSPACE}/docker-python") {
       sh "docker build -t sharifabdulcoder/app . "
     }
   }

   stage('Terraform Apply/Plan') {
           dir("${WORKSPACE}/docker-python") {
             echo "##### Pushing image to Amazon ECR ####"
             sh "docker tag http-server:latest 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
             sh "docker push 608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server:latest"
           }
         }

  stage('Terraform Destoy') {
         dir("${WORKSPACE}/artemis_tf") {
           echo "##### Terraform Destroying the Changes ####"
           sh "terraform destroy  --auto-approve"
         }
       }
