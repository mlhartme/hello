FROM debian:bullseye-20220912-slim
RUN apt-get update && \
    apt-get install -y --no-install-recommends procps curl locales openjdk-17-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    localedef -i de_DE -c -f UTF-8 -A /usr/share/locale/locale.alias de_DE.UTF-8
WORKDIR /usr/local/src
COPY maven/*.jar app.jar
ENV LANG=de_DE.UTF-8
ENTRYPOINT ["java", "-jar", "app.jar" ]
