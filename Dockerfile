FROM openjdk:21-jdk
LABEL maintainer="44wertzui44@web.de"

WORKDIR /app

COPY target/GardenShop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

