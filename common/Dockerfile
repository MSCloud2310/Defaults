FROM openjdk:17-alpine
WORKDIR /home/app
COPY ./target/*.jar app.jar 
ENV SERVER_PORT 8080
EXPOSE ${SERVER_PORT}
ENTRYPOINT [ "java", "-jar", "app.jar", "-D", "SERVER_PORT=${SERVER_PORT}" ]
