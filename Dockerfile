FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/furia-0.0.1-SNAPSHOT.jar /opt/app/japp.jar
CMD ["java", "-jar", "/opt/app/japp.jar"]

FROM ollama/ollama
COPY ./run-ollama.sh /tmp/run-ollama.sh
WORKDIR /tmp
EXPOSE 11434
