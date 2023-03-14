FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /home/app
COPY /src ./src
COPY /pom.xml .
RUN mvn clean package -D skipTests

FROM openjdk:20-ea-17-jdk AS runner
ARG DEPENDENCY=/home/app
COPY --from=build ${DEPENDENCY}/target/*.jar app.jar
ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}
ENTRYPOINT [ "java", "-jar", "./app.jar" ]
