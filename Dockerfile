FROM ubuntu:latest
LABEL authors="wilddisk"

ENTRYPOINT ["top", "-b"]

FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
EXPOSE 8443:8443
RUN mkdir /app
RUN mkdir /home/wilddisk
RUN keytool -keystore /home/wilddisk/keystore.jks -alias sampleAlias -genkeypair -keyalg RSA -keysize 4096 -validity 3 \
 -dname 'CN=localhost, OU=ktor, O=ktor, L=Unspecified, ST=Unspecified, C=US' -storepass foobar
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-docker-sample.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]
# docker build -t e-ktor .
# docker run -p 8080:8080 -p 8443:8443 eo-ktor