FROM maven:3.5-jdk-10-slim AS builder
COPY src/main /app/src/main
COPY pom.xml /app
WORKDIR /app
RUN mvn install -DskipTests

FROM openjdk:10-jre-slim
COPY --from=builder /app/target/mysim-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-Xmx300M","-jar","/app/app.jar"]