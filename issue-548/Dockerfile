FROM maven:3.5.2-jdk-8-alpine

ADD . /usr/src/mymaven

WORKDIR /usr/src/mymaven

ENTRYPOINT mvn clean test