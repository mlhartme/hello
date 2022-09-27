#!/bin/sh
docker run -it -p8080:8080 $(grep repositoryTag target/chart/values.yaml | cut -d '"' -f 2)

