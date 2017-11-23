package distributed.frontend.location;

public class Coordinate {

    private double latitude;
    private double longitude;
    private double altitude;


    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        this.altitude = 0;
    }

    public Coordinate(double latitude, double longitude, double altitude) {
        this(latitude, longitude);

        this.altitude = altitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public static Coordinate dummyCoordinates() {
        return new Coordinate(0,0);
    }
}
