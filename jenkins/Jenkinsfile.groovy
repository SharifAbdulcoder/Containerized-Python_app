node('master') {
  git 'https://github.com/SharifAbdulcoder/Docker-python.git'
  }


  stage('Build Docker') {
  if (!fileExists("Docker-python/Dockerfile")) {
         error('Dockerfile missing.')
       }
    }


  stage('Build Docker') {
    dir("${WORKSPACE}/Docker-python") {
      sh "docker build -t SharifAbdulcoder/app"
      }
    }

   stage('Build Docker') {
     dir("${WORKSPACE}/Docker-python") {
       sh "docker run -dti -p 80:8080 SharifAbdulcoder/app"
     }
  }
