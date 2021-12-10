# Changelog

## 2.0.0 (pending)

* changed from plain servlet to springboot application
* dumped cargo, run with spring-boot:run now
* changed coordinates from de.schmizzolin:hellowar:1.0.x to de.schmizzolin:hello:2.0.0
* moved sources from https://github.com/mlhartme/hellowar to https://github.com/mlhartme/hello
* renamed net.oneandone.hellowar.HelloWar to de.schmizzolin.hello.Hello
* removed proprietary dockerbuild plugin
* update parent pom 1.3.0 to 1.5.1, build for Java 16


## 1.0.5 (2019-06-24)

* Update lazy-foss-parent 1.1.0 to 1.3.0
* Info with canonical hostname
* Can be started with `mvn cargo:run` now
* checkstyle fixes


## 1.0.4 (2019-01-25)

* Info page: properly handle null pathInfo.
* update parent pom to fix Maven build


## 1.0.3 (2016-04-29)

* Info page: add ip and hostname

* Add session handling.

## 1.0.2 (2015-04-02)

* Throw demo exceptions and send configurable status codes.

## 1.0.1 (2015-04-02)

* Display servlet info, system properties and environment.


## 1.0.0 (2015-02-27)

Initial release.
