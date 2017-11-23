
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/gdibernardo/Documents/SWAN-paper/cowbird-distributed/play-java-swan-cloud/conf/routes
// @DATE:Mon Nov 20 11:31:40 CET 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseFogController FogController = new controllers.ReverseFogController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseSwanController SwanController = new controllers.ReverseSwanController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseWebSocketController WebSocketController = new controllers.ReverseWebSocketController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseFogController FogController = new controllers.javascript.ReverseFogController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseSwanController SwanController = new controllers.javascript.ReverseSwanController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseWebSocketController WebSocketController = new controllers.javascript.ReverseWebSocketController(RoutesPrefix.byNamePrefix());
  }

}
