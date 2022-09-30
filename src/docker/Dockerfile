FROM debian:bullseye-20220912-slim
RUN apt-get update && \
    apt-get install -y --no-install-recommends procps curl locales locales-all openjdk-17-jdk-headless
WORKDIR /usr/local/src
COPY maven/*-springboot.jar app.jar
ENV LANG=de_DE.UTF-8
ENTRYPOINT ["java", "-jar", "app.jar" ]
