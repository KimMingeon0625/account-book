# Dockerfile 생성
FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY . .
ENTRYPOINT ["java","-jar","/app.jar"]