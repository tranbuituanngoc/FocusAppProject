## build stage ##
FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn install -DskipTests=true
## run stage ##
FROM amazoncorretto:17-alpine3.19-jdk
WORKDIR /run
COPY --from=build /app/target/FocusAppProject-0.0.1-SNAPSHOT.jar /run/FocusAppProject-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT java -jar /run/FocusAppProject-0.0.1-SNAPSHOT.jar
