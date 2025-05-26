FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/order-0.0.1-SNAPSHOT.jar /app/order-service.jar
ENTRYPOINT ["java","-jar","order-service.jar"]
