pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {

        stage('test'){
            steps{
                // execute junit test
                sh 'mvn clean test'
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                //git 'https://github.com/ekemherve/demo-jenkins.git'

                // Run Maven on a Unix agent.
                // Skip all test(ie unit test and integration test)
                sh "mvn install -DskipTests  clean package"

                // To run Maven on a Windows agent, use
                // Skip all test(ie unit test and integration test)
                // bat "mvn install -DskipTests clean package"
            }


        stage('report') {
            steps {
                // generate surefire report
                sh 'mvn surefire-report:report'
            }
        }

        post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'target/*.jar'
            }
        }

        stage('integration test'){
            steps{
                // execute junit test
                sh 'mvn failsafe:integration-test '
            }
        }
    }
}
