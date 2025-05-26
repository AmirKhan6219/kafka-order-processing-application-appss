FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/gateway-0.0.1-SNAPSHOT.jar /app/gateway-service.jar
ENTRYPOINT ["java","-jar","gateway-service.jar"]