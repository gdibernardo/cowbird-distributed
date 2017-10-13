package cowbird.flink.common.config;

public final class Topics {
    public static final String SENSORS_VALUES_TOPIC = "STREAMS-SENSORS-VALUE-TOPIC";
    /*  Sensor Value Expression control topic.  */
    public static final String CONTROL_TOPIC_SVE = "STREAMS-CONTROL-TOPIC-SVE";
    public static final String RESULT_TOPIC = "STREAMS-RESULT-TOPIC";

    /*  New topics for more complex expressions.    */
    public static final String CONTROL_TOPIC_CVE = "STREAMS-CONTROL-TOPIC-CVE";
    public static final String CONTROL_TOPIC_CE = "STREAMS-CONTROL-TOPIC-CE";

    private Topics() {
        throw new RuntimeException("You can't create an instance of this class");
    }
}

