FROM eclipse-temurin:21-jre
WORKDIR /app
COPY build/classes/ /app/classes/
RUN mkdir -p /data
ENV QUIZ_DATA=/data
VOLUME ["/data"]
ENTRYPOINT ["java", "-cp", "/app/classes", "fr.miage.devopsquiz.Main"]
