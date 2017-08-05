package distributed.manager;

import distributed.node.CowbirdState;

import java.util.Comparator;

/**
 * Created by gdibernardo on 05/07/2017.
 */
public class CowbirdStateComparator implements Comparator<CowbirdState> {

    @Override
    public int compare(CowbirdState firstState, CowbirdState secondState) {

        /*  TO-DO: A More robust implementation is probably required.   */
        if(firstState == null)
            return 1;
        if(secondState == null)
            return -1;

        if(firstState.getCurrentLoad() < secondState.getCurrentLoad())
            return -1;
        if(firstState.getCurrentLoad() > secondState.getCurrentLoad())
            return 1;

        return 0;
    }
}
