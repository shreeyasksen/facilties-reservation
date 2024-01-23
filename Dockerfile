FROM openjdk:17

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

ENV PORT=8080

CMD ["java", "-jar", "target/facilities-reservation-0.0.1-SNAPSHOT.jar"]