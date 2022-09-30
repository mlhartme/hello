FROM debian:bullseye-20220912-slim
ENV BASE=/usr/local/app
ENV LANG=de_DE.UTF-8
RUN mkdir -p ${BASE} && \
    apt-get update && \
    apt-get install -y --no-install-recommends procps curl locales locales-all openjdk-17-jdk-headless
WORKDIR ${BASE}
COPY maven/*-springboot.jar ./app.jar
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-Dsun.jnu.encoding=UTF-8", "-jar", "app.jar" ]
