#!/usr/bin/env groovy
@Library('jenkins-shared-library')
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
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                    echo "Testing the app"
                    echo "Executing pipeline for branch $BRANCH_NAME"
                }
            }
        }
        stage('increment version'){ 
            steps { 
                script { 
                    echo 'incrementing app version'
                    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            } 
        }            
        stage("build jar") {
            steps {
                script {
                   //echo "building jar"
                   gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    //echo "building image"
                    gv.buildImage()
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
