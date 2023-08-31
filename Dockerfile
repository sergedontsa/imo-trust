FROM openjdk:17-jdk-alpine
COPY target/imob-trust-0.0.1-SNAPSHOT.jar imob-trust.jar
ENTRYPOINT ["java", "-jar", "/imob-trust"]
