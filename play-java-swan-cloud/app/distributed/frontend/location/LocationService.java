package distributed.frontend.location;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;

public class LocationService {

    private static String BASE_URL = "http://api.geoiplookup.net/?query=";

    private static LocationService locationService = new LocationService();

    public static LocationService sharedInstance() {
        return locationService;
    }

    public Coordinate getCoordinatesFromIP(String address) {

        try {
            HttpResponse<String> response = Unirest.get(BASE_URL + address)
                    .asString();
            if(response.getStatus() == 200) {

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                InputSource inputSource = new InputSource();
                inputSource.setCharacterStream(new StringReader(response.getBody()));

                Document document = documentBuilder.parse(inputSource);

                double latitude = Double.parseDouble(document.getElementsByTagName("latitude").item(0).getTextContent());
                double longitude = Double.parseDouble(document.getElementsByTagName("longitude").item(0).getTextContent());

                Coordinate coordinates = new Coordinate(latitude, longitude);
                return coordinates;
            }

        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }


        return Coordinate.dummyCoordinates();
    }



    public double distance(Coordinate firstCoordinate, Coordinate secondCoordinate) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(secondCoordinate.getLatitude() - firstCoordinate.getLatitude());
        double lonDistance = Math.toRadians(secondCoordinate.getLongitude() - firstCoordinate.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(firstCoordinate.getLatitude())) * Math.cos(Math.toRadians(secondCoordinate.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = firstCoordinate.getAltitude() - secondCoordinate.getAltitude();

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
