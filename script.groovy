def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'quay-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ihregistry.lab:8443/apps/jenkins-app:1.1 .'
        sh "echo $PASS | docker login -u $USER --password-stdin ihrgistry.lab:8443"
        sh 'docker push ihregistry.lab:8443/apps/jenkins-app:1.1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
