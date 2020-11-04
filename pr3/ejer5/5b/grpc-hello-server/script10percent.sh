#!/bin/bash
percent=45
per=1
	for i in `seq 1 10`;
do
	mvn -DskipTests exec:java -Dexec.mainClass=pdytr.example.grpc.Client -Dexec.args=" "$percent" "$per" "
	per=$((per+1))
done