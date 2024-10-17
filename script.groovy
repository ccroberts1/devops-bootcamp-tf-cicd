def buildJar() {
    echo 'building the application...'
    sh 'mvn clean package'
}

def buildImage(String imageName) {
    echo "building the docker image: $imageName"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $imageName"
    }
}

return this