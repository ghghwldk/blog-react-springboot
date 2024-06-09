FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=jar-container/*.jar
ADD ${JAR_FILE} blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/blog-0.0.1-SNAPSHOT.jar"]