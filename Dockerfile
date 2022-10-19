FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

EXPOSE 8090

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]
