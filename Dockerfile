FROM openjdk:11-jre-slim
COPY target/suburbService.jar /opt/java/suburbService.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/java/suburbService.jar"]