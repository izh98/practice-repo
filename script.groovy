def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'quay-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ihregistry.lab:8443/apps/jenkins-app:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin ihregistry.lab:8443"
        sh "docker push ihregistry.lab:8443/apps/jenkins-app:${IMAGE_NAME}"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
