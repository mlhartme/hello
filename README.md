# Hello

Simple "Hello, world" web application, implemented with springboot.

Build: mvn clean install
Run: mvn spring-boot:run

# Docker

To build and run an image:

mvn clean package -Pkubernetes
./docker-run.sh

# Kubernetes

mvn clean package -Pkubernetes
docker push <latest-snapshot>
helm upgrade hello target/chart


TODO:
* replace servlet features with spring features
* test sessions
