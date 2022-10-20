FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

EXPOSE 8090

#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./

COPY . .

#RUN ./mvnw dependency:go-offline
RUN ./mvnw package

CMD ["./mvnw", "spring-boot:run"]
