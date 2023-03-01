#FROM openjdk:19
#EXPOSE 8081
#ADD ./target/Products-1.0-SNAPSHOT-jar-with-dependencies.jar Products-1.0-SNAPSHOT-jar-with-dependencies.jar
#ENTRYPOINT ["java","-jar", "Products-1.0-SNAPSHOT-jar-with-dependencies.jar"]


FROM maven:3.8.7-openjdk-18-slim AS build
WORKDIR /app
COPY . /app
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:18
WORKDIR /app
COPY --from=build /app/Products/target/Products-1.0-SNAPSHOT-jar-with-dependencies.jar Products.jar
EXPOSE 8081
CMD ["java", "-jar", "Products.jar"]
