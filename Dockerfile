FROM bellsoft/liberica-openjdk-alpine:21
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ENTRYPOINT java -cp 'libs/*' org.testng.TestNG -threadcount ${THREAD_COUNT} test-suites/${TEST_SUITE}