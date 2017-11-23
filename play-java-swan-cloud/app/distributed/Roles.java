package distributed;

/**
 * Created by gdibernardo on 11/07/2017.
 */


public final class Roles {
    public static final String COWBIRDS_MANAGER = "cowbirds-manager";

    public static final String COWBIRD_FRONTEND = "cowbird-frontend";

    public static final String COWBIRD_FRONTEND_RESOURCE_MANAGER = COWBIRD_FRONTEND + "-manager";

    public static final String COWBIRD_NODE = "cowbird-node";

    private Roles() {
        throw new RuntimeException("You can't allocate an instance of this class");
    }
}
