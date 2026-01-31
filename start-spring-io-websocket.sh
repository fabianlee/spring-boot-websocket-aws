#!/bin/bash
#
# Creates starter Spring Boot App for websockets
# https://start.spring.io
#

id="${1:-spring-boot-websocket-aws}"
groupId="${2:-org.fabianlee}"
packageName="${groupId}.${id}"
SpringAppClassName=SpringMain
version="1.0.0"
javaVersion=25
springBootVersion=4.0.2
description="websockets+example"

# send parameters that create zip containing SpringBoot project
curl -s https://start.spring.io/starter.tgz \
    -d description=$description \
    -d type=gradle-project \
    -d configurationFileFormat=yaml \
    -d dependencies=websocket,prometheus,devtools,actuator \
    -d javaVersion=$javaVersion \
    -d bootVersion=$springBootVersion \
    -d groupId=$groupId \
    -d packageName=$packageName \
    -d artifactId=$id \
    -d name=$SpringAppClassName \
    -d baseDir=$id \
    -d packaging=jar \
    -d version=$version | tar -xzvf -

# copy this script to directory just created, for posterity
cp $(basename $0) $id/.

