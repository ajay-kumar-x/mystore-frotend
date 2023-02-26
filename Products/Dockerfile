FROM openjdk:19
EXPOSE 8081
ADD ./target/Products-1.0-SNAPSHOT-jar-with-dependencies.jar Products-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar", "Products-1.0-SNAPSHOT-jar-with-dependencies.jar"]