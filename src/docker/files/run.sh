#!/bin/sh
export LANG=de_DE.UTF-8
# invoke with exec to run java as pid 1 and thus properly receive signals
exec java -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -jar *.jar
