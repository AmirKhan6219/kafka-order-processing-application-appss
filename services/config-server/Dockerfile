FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar
ENTRYPOINT ["java","-jar","config-server.jar"]
