#!/bin/bash


if [ $# -ne 1 ]; then
echo usage: ./$0 [path-to-Kafka ..]
exit 1
fi


path_to_kafka=$1


sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic STREAMS-SENSORS-VALUE-TOPIC

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic STREAMS-CONTROL-TOPIC-SVE

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic STREAMS-CONTROL-TOPIC-CVE

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic STREAMS-CONTROL-TOPIC-CE

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic STREAMS-RESULT-TOPIC
