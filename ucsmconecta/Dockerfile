# Etapa 1: Construcción con Maven y JDK 17
FROM openjdk:17-jdk-slim
ENV TZ=America/Lima
ARG JAR_FILE=target/ucsmconecta-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_ucsmconecta.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app_ucsmconecta.jar"]
