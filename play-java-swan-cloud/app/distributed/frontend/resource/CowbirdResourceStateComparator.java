package distributed.frontend.resource;

import distributed.frontend.resource.CowbirdResourceState;

import java.util.Comparator;

public class CowbirdResourceStateComparator implements Comparator <CowbirdResourceState> {
    @Override
    public int compare(CowbirdResourceState firstState, CowbirdResourceState secondState) {
        if(firstState == null)
            return 1;
        if(secondState == null)
            return -1;

        if(firstState.getDistanceFromRequest() < secondState.getDistanceFromRequest())
            return  -1;

        if(firstState.getDistanceFromRequest() > secondState.getDistanceFromRequest())
            return 1;

        return 0;
    }
}
