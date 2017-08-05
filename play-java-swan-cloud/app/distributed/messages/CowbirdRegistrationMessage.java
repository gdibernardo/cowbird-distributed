package distributed.messages;


import distributed.node.CowbirdState;

import java.io.Serializable;

/**
 * Created by gdibernardo on 05/07/2017.
 */


public class CowbirdRegistrationMessage implements Serializable {

    private CowbirdState cowbirdState;


    public CowbirdState getCowbirdState() {
        return cowbirdState;
    }


    public CowbirdRegistrationMessage(CowbirdState state) {
        cowbirdState = state;
    }
}
