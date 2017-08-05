package cowbird.flink.common.abs;

public interface MessageInterface {

    String toJSON();

    void initFromJSON(String json);
}
