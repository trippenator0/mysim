FROM maven:3.5-jdk-10-slim AS builder
RUN mvn clean install -DskipTests

FROM openjdk:10-jre-slim
COPY --from=builder target/mysim-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]