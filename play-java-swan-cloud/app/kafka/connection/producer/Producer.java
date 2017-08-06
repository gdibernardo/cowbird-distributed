package kafka.connection.producer;


import cowbird.flink.common.config.Topics;
import cowbird.flink.common.messages.control.ComplexCompareControlMessage;
import cowbird.flink.common.messages.control.ConstantCompareControlMessage;
import cowbird.flink.common.messages.control.ControlMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;

import kafka.connection.Config;
import kafka.connection.consumer.Consumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;

public class Producer {

    private ArrayList<String> producingExpressions = new ArrayList<>();

    /*  Kafka producer is thread-safe.  */
    private KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(Config.defaultProducingProperties());
    private static Producer producer = new Producer();

    static public Producer sharedProducer() {
        return producer;
    }


    public void send(SensorMessage sensorMessage) {
        send(Topics.SENSORS_VALUES_TOPIC, sensorMessage.getExpressionId(), sensorMessage.toJSON());
    }


    private void handleConsumerEntryForControlMessages(String expressionId) {

        if(producingExpressions.contains(expressionId)) {
            producingExpressions.remove(expressionId);
            Consumer.sharedConsumer().removeEntry(expressionId);
        } else {
            producingExpressions.add(expressionId);
            Consumer.sharedConsumer().addEntry(expressionId);
        }
    }

    public void send(ControlMessage controlMessage) {

        String expressionId = controlMessage.getExpressionId();

        handleConsumerEntryForControlMessages(expressionId);


        String topic = Topics.CONTROL_TOPIC_SVE;
        if(controlMessage instanceof ConstantCompareControlMessage)
            topic = Topics.CONTROL_TOPIC_CVE;

        send(topic, controlMessage.getExpressionId(), controlMessage.toJSON());
    }


    public void send(ComplexCompareControlMessage controlMessage) {

        String expressionId = controlMessage.getExpressionId();

        handleConsumerEntryForControlMessages(expressionId);

        send(Topics.CONTROL_TOPIC_CE, controlMessage.getExpressionId(), controlMessage.toJSON());
    }


    private void send(String topic, String key, String payload) {
        ProducerRecord record = new ProducerRecord(topic, key, payload);
        kafkaProducer.send(record);
    }


    public void destroy() {
        kafkaProducer.close();
    }
}
