package kafka.connection;


import distributed.node.CowbirdConfiguration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Config {

    static final String COWBIRD_CONSUMER_GROUP_ID_CONFIG = "COWBIRD_CONSUMER_GROUP_ID_CONFIG_";

    public static Properties defaultProducingProperties() {
        Properties properties = new Properties();
        // properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        // properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-gdiberna-0.sda.surf-hosted.nl:9092");


        return properties;
    }


    public static Properties defaultConsumingProperties() {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        /*  Defining a consumer group for each Cowbird Node. This approach should be definitely improved.   */
        String groupIdentifier = COWBIRD_CONSUMER_GROUP_ID_CONFIG + CowbirdConfiguration.nodeConfiguration().hashCode();

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdentifier);

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-gdiberna-0.sda.surf-hosted.nl:9092");

        return properties;
    }
}
