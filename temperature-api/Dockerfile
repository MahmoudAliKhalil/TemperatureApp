FROM maven:3.8-jdk-11 as builder
WORKDIR application
COPY . .
RUN mvn clean install
RUN java -Djarmode=layertools -jar target/*.jar extract

FROM openjdk:11
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
