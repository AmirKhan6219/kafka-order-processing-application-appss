FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/customer-0.0.1-SNAPSHOT.jar /app/customer-service.jar
ENTRYPOINT ["java","-jar","customer-service.jar"]