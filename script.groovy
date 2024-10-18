def buildJar() {
    echo 'building the application...'
    sh 'mvn clean package'
}

def buildImage() {
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId: 'docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ccroberts1/demo-app:java-maven-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push ccroberts1/demo-app:java-maven-2.0'
    }
}

return this