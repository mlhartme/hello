FROM debian:bullseye-20220912-slim
RUN apt-get update && \
    apt-get install -y --no-install-recommends procps curl openjdk-17-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /usr/local/src
COPY maven/*.jar app.jar
ENV LANG=C.UTF-8
ENTRYPOINT ["java", "-jar", "app.jar" ]
