import java.util.Random;

public class Sensor {

    String id;

    Random rand = new Random();

    float min = 0.0f;
    float max = 130.0f;

    public float getData() {
        return rand.nextFloat() * (max - min) + min;
    }
}
