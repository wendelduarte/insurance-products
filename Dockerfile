FROM maven:3-openjdk-17 AS build
COPY src /home/products/src
COPY pom.xml /home/products
RUN mvn -f /home/products/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /home/products/target/*.jar /usr/local/lib/products/*.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/products/*.jar"]