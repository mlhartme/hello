FROM debian:sid-20220912-slim
ENV LANG de_DE.UTF-8
ENV OPTS ""
RUN mkdir -p /usr/share/man/man1 && \
    apt-get update && \
    apt-get install -y --no-install-recommends procps curl locales locales-all openjdk-17-jdk-headless && \
    mkdir /usr/local/hello && \
    mkdir /usr/local/hello/run

COPY maven/*-springboot.jar /usr/local/hello/hello.jar

RUN export RUN=/usr/local/hello/hello.sh && \
    echo "#!/bin/sh" >$RUN && \
    echo "exec java -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 \$OPTS -jar /usr/local/hello/hello.jar" >>$RUN && \
    chmod a+x $RUN
ENTRYPOINT ["/usr/local/hello/hello.sh"]
