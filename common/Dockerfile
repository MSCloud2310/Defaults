FROM maven:3.8.5-openjdk-17-slim
WORKDIR /home/app
COPY src/ src/
COPY pom.xml .
RUN mvn clean package
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "./target/*.jar" ]
