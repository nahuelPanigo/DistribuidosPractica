#!/bin/bash
	for i in `seq 1 50`;
do
	mvn -DskipTests exec:java -Dexec.mainClass=pdytr.example.grpc.Client
done
	