package kafka.connection.consumer;

import cowbird.flink.common.config.Topics;

import cowbird.flink.common.messages.result.ResultMessage;

import kafka.connection.Config;

import kafka.connection.LatencyController;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;

class ConsumerLoop implements Runnable {

    static private long DEFAULT_POLL_TIMEOUT = Long.MAX_VALUE;
    private KafkaConsumer<String, String> kafkaConsumer;

    private String consumerLoopId;

    Consumer consumer;

    ConsumerLoop(String consumerLoopId, Consumer consumer) {
        this.consumerLoopId = consumerLoopId;
        this.consumer = consumer;

        kafkaConsumer = new KafkaConsumer<>(Config.defaultConsumingProperties());
        kafkaConsumer.subscribe(Arrays.asList(Topics.RESULT_TOPIC));
    }


    String getConsumerLoopId() {
        return consumerLoopId;
    }


    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(DEFAULT_POLL_TIMEOUT);
                for(ConsumerRecord<String, String> record : records) {

                    /*  JSON formatting adds extra latency.   */
                    ResultMessage resultMessage = new ResultMessage();
                    resultMessage.initFromJSON(record.value());

                    String identifier = resultMessage.getExpressionId();

                    long taken = System.currentTimeMillis() - LatencyController.sharedInstance().get(identifier);

                    System.out.println("Got some result from Kafka. Latency: " + taken + " ms");

                    LatencyController.sharedInstance().remove(identifier);

                    consumer.appendResult(resultMessage);
                }
            }
        } catch (WakeupException e) {
            /*  Shutdown the consumer.  */
        } finally {
            kafkaConsumer.close();
        }
    }


    void destroy() {
        kafkaConsumer.wakeup();
    }
}
