#FROM openjdk:18
#EXPOSE 80
#ADD ./target/WebInterface-1.0-SNAPSHOT-jar-with-dependencies.jar WebInterface-1.0-SNAPSHOT-jar-with-dependencies.jar
#ENTRYPOINT ["java","-jar", "WebInterface-1.0-SNAPSHOT-jar-with-dependencies.jar"]


FROM maven:3.8.7-openjdk-18-slim
WORKDIR /webmodule
COPY ./WebInterface/pom.xml /webmodule
COPY ./WebInterface/src /webmodule/src
RUN mvn package

EXPOSE 80
CMD ["java", "-jar", "target/WebInterface-1.0-SNAPSHOT-jar-with-dependencies.jar"]
