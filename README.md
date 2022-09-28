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

create a gh persoanl access token with package permissions
echo $GH_PAT| docker login ghcr.io -u mlhartme --password-stdin
docker push <latest-snapshot>

wait a few minutes before you can see it one the web page

helm upgrade hello target/chart

helm package target/chart 

helm push hello-2.0.0-20220928-201437.tgz oci://ghcr.io/mlhartme/charts

helm install hello oci://ghcr.io/mlhartme/hello --version 2.0.0-20220927-203032

TODO:
* replace servlet features with spring features
* test sessions
