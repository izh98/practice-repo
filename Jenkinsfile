def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.9.6'
    }
    parameters {
        choice(name: 'VERSION', choices: ['1.0', '1.1', '1.2'], description: '')
               booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    stages {
        stage("test") {
            steps {
                script {
                    gv = load "script.groovy"
                    echo "Testing the app"
                    echo "Executing pipeline for branch $BRANCH_NAME"
                }
            }
        }
        stage("build jar") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                   //echo "building jar"
                   gv.buildJar()
                }
            }
        }
        stage("build image") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    //echo "building image"
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            when{
                expression{
                    BRANCH_NAME == 'main'
                }
            }
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
