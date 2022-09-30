# Hello

Simple "Hello, world" web application, implemented with springboot.

Build: mvn clean install
Run: mvn spring-boot:run

# Docker

To build and run an image:

mvn clean package -Pkubernetes
./docker-run.sh

# Kubernetes

Github setup:
* create a gh personal access token with package permissions
* echo $GH_PAT| docker login ghcr.io -u mlhartme --password-stdin

Deploy a helm chart and the docker image:

mvn clean deploy -Pkubernetes

(package show up here, maybe with some delay: https://github.com/mlhartme?tab=packages )

install (or upgrade): 

helm install hello oci://ghcr.io/mlhartme/charts/hello --version 2.0.0-20220930-171009

TODO:
* replace servlet features with spring features
