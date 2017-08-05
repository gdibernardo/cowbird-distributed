package cowbird.flink.common.config;

public final class Topics {
    public static final String SENSORS_VALUES_TOPIC = "SENSORS-VALUE-TOPIC";
    /*  Sensor Value Expression control topic.  */
    public static final String CONTROL_TOPIC_SVE = "CONTROL-TOPIC-SVE";
    public static final String RESULT_TOPIC = "RESULT-TOPIC";

    /*  New topics for more complex expressions.    */
    public static final String CONTROL_TOPIC_CVE = "CONTROL-TOPIC-CVE";
    public static final String CONTROL_TOPIC_CE = "CONTROL-TOPIC-CE";


    private Topics() throws Exception {
        throw new Exception("You can't create an instance of this class");
    }
}
