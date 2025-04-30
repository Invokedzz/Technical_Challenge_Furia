FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/furia-0.0.1-SNAPSHOT.jar /opt/app/japp.jar
CMD ["java", "-jar", "/opt/app/japp.jar"]
