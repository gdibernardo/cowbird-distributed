#!/bin/bash


if [ $# -ne 1 ]; then
echo $0: usage: ./init_kafka_locally.sh [path-to-Kafka ..]
exit 1
fi


path_to_kafka=$1


sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic SENSORS-VALUE-TOPIC

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic CONTROL-TOPIC-SVE

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic CONTROL-TOPIC-CVE

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic CONTROL-TOPIC-CE

sh $path_to_kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic RESULT-TOPIC
