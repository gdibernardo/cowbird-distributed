package distributed.frontend.resource;

import distributed.frontend.location.Coordinate;
import distributed.node.CowbirdState;

public class CowbirdResourceState {

    private CowbirdState state;

    private int resourceUtilization;

    private double distanceFromRequest;

    private Coordinate coordinates;

    public CowbirdResourceState(CowbirdState state) {
        this.state = state;

        resourceUtilization = 0;

        distanceFromRequest = 0;
    }

    public void setResourceUtilization(int resourceUtilization) {
        this.resourceUtilization = resourceUtilization;
    }

    public int getResourceUtilization() {
        return resourceUtilization;
    }

    public void setState(CowbirdState state) {
        this.state = state;
    }

    public CowbirdState getState() {
        return state;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setDistanceFromRequest(double distanceFromRequest) {
        this.distanceFromRequest = distanceFromRequest;
    }

    public double getDistanceFromRequest() {
        return distanceFromRequest;
    }
}
