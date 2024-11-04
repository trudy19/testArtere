FROM openjdk:17-oracle
COPY ./target/aertereTest-0.0.1-SNAPSHOT.jar aertereTest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","aertereTest-0.0.1-SNAPSHOT.jar"]