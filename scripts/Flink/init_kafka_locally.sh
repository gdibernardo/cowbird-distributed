#!/bin/bash


if [ $# -ne 1 ]; then
    echo usage: ./init_kafka_locally.sh [path-to-Kafka ..]
    exit 1
fi


path_to_kafka=$1

echo "Starting Zookeeper server.... check log file at zookeeper-logs"
nohup $path_to_kafka/bin/zookeeper-server-start.sh $path_to_kafka/config/zookeeper.properties > zookeeper-logs &

echo "Starting Kafka server.... check log file at kafka-logs"
nohup $path_to_kafka/bin/kafka-server-start.sh $path_to_kafka/config/server.properties > kafka-logs &




