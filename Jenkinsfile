pipeline {
    agent any

    //If maven is configured in jenkins, then we dont need to specify maven here unless we need a specific version for this job
    //tools {
        // Install the Maven version configured as "maven-3-6-0" and add it to the path.
        //maven "Maven-3-6-0"
    //}

    // This will trigger a poll every minute and will make a build if there is a new commit in our remote SCM(Github in this case) repository
    triggers {
        pollSCM '*/1 * * * *'
    }

    stages {

        stage('print'){
            steps{
                // print all actual directory content
                sh 'ls -a'
                // print src directory content
                sh 'ls src'
                // print src/main directory content
                sh 'ls src/main'
                // print src/test directory content
                sh 'ls src/test'
                // print src/main/java directory content
                sh 'ls src/main/java'
                // print src/main/resources directory content
                sh 'ls src/main/resources'
            }
        }

        stage('version'){
            steps{
                // check maven version
                sh 'mvn -v'
                sh 'java -version'
            }
        }

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
        }

        stage('report') {
            steps {
                // generate surefire report
                sh 'mvn surefire-report:report'
            }
        }

        stage('integration test'){
            steps{
                // execute junit test
                sh 'mvn failsafe:integration-test '
            }
        }

        stage('site'){
            steps{
                // execute junit test
                sh 'mvn site '
            }
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
}
