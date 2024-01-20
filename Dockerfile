
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY . .


RUN mvn clean install -DskipTests


FROM openjdk:17

WORKDIR /app


 ENV PORT=8080

COPY --from=builder /app/target/facilities-reservation-0.0.1-SNAPSHOT.jar /app/facilities-reservation.jar

CMD ["java", "-jar", "facilities-reservation.jar"]


















