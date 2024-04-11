def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.0', '1.1'], description: '')
               booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                   echo "building jar"
                   //gv.buildJar()
                }
            }
        }
        stage("build image") {

            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    echo "building image"
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
            input{
                message "Select the environment to deploy to"
                ok "Done"
                parameters {
                    choice(name: 'ONE', choices: ['DEV', 'Staging', 'Prod'], description: '')
                    choice(name: 'TWO', choices: ['DEV', 'Staging', 'Prod'], description: '')
                }
            }
            steps {
                script {
                    echo "deploying"
                    echo "deploying version ${params.VERSION}"
                    echo "deploying to ${ONE}"
                    echo "deploying to ${TWO}"
                    //gv.deployApp()
                }
            }
        }
    }   
}
