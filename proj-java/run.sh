#!/bin/bash

rm -rf *.txt target/

mvn clean package
#-Xmx4096m
mycommand="java -Xmx4096m -jar target/project2-1.0-SNAPSHOT-jar-with-dependencies.jar "$@
echo $mycommand
$mycommand
