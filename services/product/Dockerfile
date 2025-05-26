FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app
COPY build/libs/product-0.0.1-SNAPSHOT.jar /app/product-service.jar
ENTRYPOINT ["java","-jar","product-service.jar"]
