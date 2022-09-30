# Hello

Simple "Hello, world" web application, implemented with spring boot.

Build: mvn clean install
Run: mvn spring-boot:run

TODO:
* replace servlet features with spring features

# Docker

To build and run an image:

mvn clean package -Pkubernetes
./docker-run.sh

# Deploy image and chart

Github setup:
* create a gh personal access token with package permissions
* echo $GH_PAT| docker login ghcr.io -u mlhartme --password-stdin

Deploy a helm chart and the docker image:

mvn clean deploy -Pkubernetes

(package show up here, maybe with some delay: https://github.com/mlhartme?tab=packages)

# Publish to cluster

Install (or upgrade) Helm release

helm install hello oci://ghcr.io/mlhartme/charts/hello --version 2.0.0-20220930-171009

