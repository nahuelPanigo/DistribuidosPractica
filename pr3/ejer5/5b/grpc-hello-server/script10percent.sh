#!/bin/bash
percent=45
	for i in `seq 0 10`;
do
	mvn -DskipTests exec:java -Dexec.mainClass=pdytr.example.grpc.Client -Dexec.args=" "$percent" "$i" "
done