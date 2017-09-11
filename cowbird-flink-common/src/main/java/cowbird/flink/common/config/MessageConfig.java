package cowbird.flink.common.config;

public final class MessageConfig {

    public static final String ID_CONFIG_KEY = "ID";

    public static final String INGESTION_TIME_CONFIG_KEY = "INGESTION_TIME_CONFIG_KEY";

    public static final String EVENT_TIME_CONFIG_KEY = "EVENT_TIME_CONFIG_KEY";

    public static final String VALUE_CONFIG_KEY = "VALUE_CONFIG_KEY";

    public static final String HISTORY_REDUCTION_MODE_CONFIG_KEY = "HISTORY_REDUCTION_MODE_CONFIG_KEY";

    public static final String HISTORY_LENGTH_CONFIG_KEY = "HISTORY_LENGTH_CONFIG_KEY";

    public static final String COMPARATOR_CONFIG_KEY = "COMPARATOR_CONFIG_KEY";

    public static final String IS_EXPRESSION_LEFT_CONFIG_KEY = "IS_EXPRESSION_LEFT_CONFIG_KEY";

    public static final String LEFT_OLDEST_TIMESTAMP_CONFIG_KEY = "LEFT_OLDEST_TIMESTAMP_CONFIG_KEY";
    public static final String LEFT_LATEST_TIMESTAMP_CONFIG_KEY = "LEFT_LATEST_TIMESTAMP_CONFIG_KEY";

    public static final String RIGHT_OLDEST_TIMESTAMP_CONFIG_KEY = "RIGHT_OLDEST_TIMESTAMP_CONFIG_KEY";
    public static final String RIGHT_LATEST_TIMESTAMP_CONFIG_KEY = "RIGHT_LATEST_TIMESTAMP_CONFIG_KEY";

    public static final String LEFT_ID_CONFIG_KEY = "LEFT_ID_CONFIG_KEY";
    public static final String RIGHT_ID_CONFIG_KEY = "RIGHT_ID_CONFIG_KEY";

    public static final String LEFT_HISTORY_REDUCTION_MODE_CONFIG_KEY = "LEFT_HISTORY_REDUCTION_MODE_CONFIG_KEY";
    public static final String RIGHT_HISTORY_REDUCTION_MODE_CONFIG_KEY = "RIGHT_HISTORY_REDUCTION_MODE_CONFIG_KEY";

    public static final String LEFT_HISTORY_LENGTH_CONFIG_KEY = "LEFT_HISTORY_LENGTH_CONFIG_KEY";
    public static final String RIGHT_HISTORY_LENGTH_CONFIG_KEY = "RIGHT_HISTORY_LENGTH_CONFIG_KEY";

    private MessageConfig() {
        throw new RuntimeException("You can't create an instance of this class");
    }
}
