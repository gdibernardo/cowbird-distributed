package distributed.manager;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import distributed.Roles;


/**
 * Created by gdibernardo on 03/07/2017.
 */


public class CowbirdManagerApp {

    public static void main(String[] args) {
        startUp(args);
    }


    public static void startUp(String[] args) {

        final String port = args.length > 0 ? args[0] : "0";
        final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
                withFallback(ConfigFactory.parseString(String.format("akka.cluster.roles = [%s]", Roles.COWBIRDS_MANAGER))).
                withFallback(ConfigFactory.load());
        ActorSystem system = ActorSystem.create("CowbirdClusterSystem", config);

        final ActorRef manager = system.actorOf(CowbirdManager.props(), Roles.COWBIRDS_MANAGER);
    }
}
