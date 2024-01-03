FROM ubuntu:latest
LABEL authors="wilddisk"

ENTRYPOINT ["top", "-b"]

#FROM gradle:7-jdk11 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle buildFatJar --no-daemon
#RUN gradle clean build

FROM openjdk:11
EXPOSE 8082:8082
EXPOSE 9443:9443
RUN mkdir /app
RUN mkdir /home/wilddisk
RUN keytool -keystore /home/wilddisk/keystore.jks -alias sampleAlias -genkeypair -keyalg RSA -keysize 4096 -validity 3 \
 -dname 'CN=localhost, OU=ktor, O=ktor, L=Unspecified, ST=Unspecified, C=US' -storepass foobar
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-docker-sample.jar
#COPY build/libs/eo-ktor-sample.jar /app/eo-ktor-sample.jar
COPY build/libs/app.jar /app/eo-ktor-sample.jar
ENTRYPOINT ["java","-jar","/app/eo-ktor-sample.jar"]

# docker build -t eo-ktor .
# docker run -p 8082:8082 -p 9443:9443 eo-ktor