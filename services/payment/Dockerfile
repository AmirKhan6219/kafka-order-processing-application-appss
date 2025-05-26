FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/payment-0.0.1-SNAPSHOT.jar /app/payment-service.jar
ENTRYPOINT ["java","-jar","payment-service.jar"]
