#!/bin/bash
percent=45
	for i in `seq 1 50`;
do
	`echo ((percent+1))`
	#mvn -DskipTests exec:java -Dexec.mainClass=pdytr.example.grpc.Client -Dexec.args="myArg1 myArg2"
done