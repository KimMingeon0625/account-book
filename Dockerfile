# Dockerfile 생성
FROM openjdk:11
COPY ${buildx-cache} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]