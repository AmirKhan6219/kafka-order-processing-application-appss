FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/notification-0.0.1-SNAPSHOT.jar /app/notification-service.jar
ENTRYPOINT ["java","-jar","notification-service.jar"]
