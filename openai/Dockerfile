FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /crm
COPY . .

RUN mvn clean package -pl openai -am -DskipTests

FROM openjdk:21

WORKDIR /application
COPY --from=build /crm/openai/target/*.jar app.jar

WORKDIR /application
CMD ["java", "-jar", "app.jar"]

EXPOSE 8081
