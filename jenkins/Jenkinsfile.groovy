node('master') {
    git 'https://github.com/SharifAbdulcoder/Docker-python.git'
  }

  stage('Build an image') {
    sample = docker.build("608022717509.dkr.ecr.us-east-1.amazonaws.com/http-server")
  }

  withCredentials([])
