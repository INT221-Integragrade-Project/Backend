FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
EXPOSE 80
ARG JAR_FILE=target/project-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
