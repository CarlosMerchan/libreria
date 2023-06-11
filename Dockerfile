FROM openjdk:17-jdk-alpine
COPY target/biblioteca-0.0.1-SNAPSHOT.jar biblioteca_app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","biblioteca_app.jar" ]
