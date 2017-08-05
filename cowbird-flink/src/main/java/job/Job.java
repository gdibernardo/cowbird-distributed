package job;

import cowbird.flink.common.util.Utils;
import cowbird.flink.common.config.Topics;

import cowbird.flink.common.messages.control.ConstantCompareControlMessage;
import cowbird.flink.common.messages.control.ControlMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;

import job.swan.core.ConstantCompareFlatMap;
import job.swan.core.SWANProcessFunction;

import org.apache.flink.api.common.functions.MapFunction;

import org.apache.flink.api.java.tuple.Tuple2;

import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;

import org.apache.flink.streaming.api.TimeCharacteristic;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * Created by gdibernardo on 17/07/2017.
 */

public class Job {


    /*  This can be a path on HDFS. */
    private static final String ROCKSDB_STATE_PATH = "file:///checkpoints/";

    private static final long CHECKPOINT_INTERVAL = 5000; // ms

    public static final String CONSUMER_FLINK_SENSORS_VALUES_GROUP_ID = "CONSUMER_FLINK_SENSORS_VALUES_GROUP_ID";

    public static final String CONSUMER_FLINK_CONTROL_TOPIC_SVE_GROUP_ID = "CONSUMER_FLINK_CONTROL_TOPIC_SVE_GROUP_ID";
    public static final String CONSUMER_FLINK_CONTROL_TOPIC_CVE_GROUP_ID = "CONSUMER_FLINK_CONTROL_TOPIC_CVE_GROUP_ID";
    public static final String CONSUMER_FLINK_CONTROL_TOPIC_CE_GROUP_ID = "CONSUMER_FLINK_CONTROL_TOPIC_CE_GROUP_ID";

    public static final String CLIENT_ID = "CLIENT_ID";

    public static Properties defaultConsumingProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        return properties;
    }

    public static void main(String args[]) throws Exception {

        /*  Create streaming environment.   */
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        // environment.enableCheckpointing(CHECKPOINT_INTERVAL, CheckpointingMode.AT_LEAST_ONCE);
        /*  Setting RocksDB backend.    */
        // environment.setStateBackend(new RocksDBStateBackend(ROCKSDB_STATE_PATH));
        /*  Init sensors values Kafka source.   */
        Properties sensorValuesConsumerProperties = defaultConsumingProperties();
        sensorValuesConsumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_FLINK_SENSORS_VALUES_GROUP_ID);
        FlinkKafkaConsumer010<String> sensorsValuesConsumer = new FlinkKafkaConsumer010<String>(Topics.SENSORS_VALUES_TOPIC,
                new SimpleStringSchema(),
                sensorValuesConsumerProperties);

        /*  Init control sensor value expressions Kafka source. */
        Properties controlsConsumerSVEProperties = defaultConsumingProperties();
        controlsConsumerSVEProperties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_FLINK_CONTROL_TOPIC_SVE_GROUP_ID);
        FlinkKafkaConsumer010<String> controlsConsumerSVE = new FlinkKafkaConsumer010<String>(Topics.CONTROL_TOPIC_SVE, new SimpleStringSchema(), controlsConsumerSVEProperties);

        /*  Init control comparison constant value expression Kafka source. */
        Properties controlsConsumerCVEProperties = defaultConsumingProperties();
        controlsConsumerCVEProperties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_FLINK_CONTROL_TOPIC_CVE_GROUP_ID);
        FlinkKafkaConsumer010<String> controlsConsumerCVE = new FlinkKafkaConsumer010<String>(Topics.CONTROL_TOPIC_CVE, new SimpleStringSchema(), controlsConsumerCVEProperties);

        /* Init control complex expressions Kafka source.   */
        Properties controlsConsumerCEProperties = defaultConsumingProperties();
        controlsConsumerCEProperties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_FLINK_CONTROL_TOPIC_CE_GROUP_ID);
        FlinkKafkaConsumer010<String> controlsConsumerCE = new FlinkKafkaConsumer010<String>(Topics.CONTROL_TOPIC_CE, new SimpleStringSchema(), controlsConsumerCEProperties);


        FlinkKafkaProducer010<String> kafkaProducer = new FlinkKafkaProducer010("localhost:9092", Topics.RESULT_TOPIC, new SimpleStringSchema());

                DataStream<Tuple2<String,ControlMessage>> controlStreamSVE = environment.addSource(controlsConsumerSVE)
                .rebalance()
                .map(new MapFunction<String, Tuple2<String, ControlMessage>>() {
                    @Override
                    public Tuple2<String, ControlMessage> map(String json) throws Exception {
                        ControlMessage controlMessage = new ControlMessage();
                        controlMessage.initFromJSON(json);

                        return new Tuple2<>(controlMessage.getExpressionId(), controlMessage);
                    }
                });

        DataStream<Tuple2<String,SensorMessage>> sensorsValuesStream = environment.addSource(sensorsValuesConsumer)
                .rebalance()
                .map(new MapFunction<String, Tuple2<String,SensorMessage>>() {
                    @Override
                    public Tuple2<String,SensorMessage> map(String json) throws Exception {
                        SensorMessage sensorMessage = new SensorMessage();
                        sensorMessage.initFromJSON(json);

                        return new Tuple2<>(Utils.getParentForExpression(sensorMessage.getExpressionId()), sensorMessage);
                    }
               });


        DataStream<Tuple2<String, ConstantCompareControlMessage>> controlStreamCVE = environment.addSource(controlsConsumerCVE)
                .rebalance()
                .map(new MapFunction<String, Tuple2<String, ConstantCompareControlMessage>>() {
                    @Override
                    public Tuple2<String, ConstantCompareControlMessage> map(String json) throws Exception {
                        ConstantCompareControlMessage constantValueCompareMessage = new ConstantCompareControlMessage();
                        constantValueCompareMessage.initFromJSON(json);

                        return new Tuple2<>(constantValueCompareMessage.getExpressionId(), constantValueCompareMessage);
                    }
                });


        ConnectedStreams<Tuple2<String, ControlMessage>, Tuple2<String, SensorMessage>> connectedStreamsSVE = controlStreamSVE
                .connect(sensorsValuesStream)
                .keyBy(0,0);

        connectedStreamsSVE.process(new SWANProcessFunction())
                .map(message -> message.toJSON())
                .rebalance()
                .addSink(kafkaProducer);


        ConnectedStreams<Tuple2<String, ConstantCompareControlMessage>, Tuple2<String, SensorMessage>> connectedStreamsCVE = controlStreamCVE
                .connect(sensorsValuesStream)
                .keyBy(0,0);

        connectedStreamsCVE.flatMap(new ConstantCompareFlatMap())
                .map(message -> message.toJSON())
                .rebalance()
                .addSink(kafkaProducer);
//        connectedStreams.flatMap(new ProcessMap())
//                .rebalance()
//                .map(message -> message.toJSON())
//                .addSink(new FlinkKafkaProducer010("localhost:9092", Topics.RESULT_TOPIC, new SimpleStringSchema()));


        environment.execute();
    }
}
