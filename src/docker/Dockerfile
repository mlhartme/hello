FROM debian:bullseye-20220912-slim
ENV BASE=/usr/local/app
RUN mkdir -p /usr/share/man/man1 && \
    mkdir -p ${BASE} && \
    apt-get update && \
    apt-get install -y --no-install-recommends procps curl locales locales-all openjdk-17-jdk-headless
WORKDIR ${BASE}
COPY files/* .
COPY maven/*-springboot.jar ./app.jar
ENTRYPOINT ["./run.sh"]
