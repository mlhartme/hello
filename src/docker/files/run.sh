#!/bin/sh
export LANG=de_DE.UTF-8
# invoke with exec to have proper kill signal handling
exec java -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -jar *.jar
