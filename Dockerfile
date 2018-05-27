FROM openjdk:10-jre-slim
COPY target/mysim-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]