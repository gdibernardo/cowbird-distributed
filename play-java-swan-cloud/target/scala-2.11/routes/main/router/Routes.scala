
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/gdibernardo/Documents/SWAN-paper/cowbird-distributed/play-java-swan-cloud/conf/routes
// @DATE:Mon Nov 20 11:31:40 CET 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  SwanController_0: controllers.SwanController,
  // @LINE:125
  FogController_1: controllers.FogController,
  // @LINE:143
  WebSocketController_2: controllers.WebSocketController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    SwanController_0: controllers.SwanController,
    // @LINE:125
    FogController_1: controllers.FogController,
    // @LINE:143
    WebSocketController_2: controllers.WebSocketController
  ) = this(errorHandler, SwanController_0, FogController_1, WebSocketController_2, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, SwanController_0, FogController_1, WebSocketController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.SwanController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swansong""", """controllers.SwanController.swanSongFormService"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swansongjson""", """controllers.SwanController.swanSongJSONService"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """push""", """controllers.SwanController.testWebService"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registerrainvalue""", """controllers.SwanController.testRegisterRainValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregisterrainvalue""", """controllers.SwanController.testUnregisterRainValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registerraintristate""", """controllers.SwanController.testRegisterRainTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregisterraintristate""", """controllers.SwanController.testUnregisterRainTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/test/value""", """controllers.SwanController.testRegisterTestValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/test/value""", """controllers.SwanController.testUnregisterTestValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registertesttristate""", """controllers.SwanController.testRegisterTestTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregistertesttristate""", """controllers.SwanController.testUnregisterTestTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registerSVE""", """controllers.SwanController.testSVEFlink"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """testSound""", """controllers.SwanController.testSound"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """testSoundLocal""", """controllers.SwanController.testSound_local"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registercurrencyvalue""", """controllers.SwanController.testRegisterCurrencyValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregistercurrencyvalue""", """controllers.SwanController.testUnregisterCurrencyValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registercurrencytristate""", """controllers.SwanController.testRegisterCurrencyTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregistercurrencytristate""", """controllers.SwanController.testUnregisterCurrencyTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """stop""", """controllers.SwanController.stop"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registerall""", """controllers.SwanController.testRegisterAll"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregisterall""", """controllers.SwanController.testUnregisterAll"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registeremail""", """controllers.SwanController.registerExpressionForEmailNotification"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregisteremail""", """controllers.SwanController.unRegisterExpressionForEmailNotification"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """webhook/""", """controllers.SwanController.setupWebHook"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """webhook/""", """controllers.SwanController.requestWebHookSwanBot"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/register/""", """controllers.SwanController.swanPhoneRegister"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/unregister/""", """controllers.SwanController.swanPhoneUnregister"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/lora/value""", """controllers.SwanController.testRegisterLoraValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/lora/value""", """controllers.SwanController.testUnregisterLoraValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/thingspeak/value""", """controllers.SwanController.testRegisterThingSpeakValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/thingspeak/value""", """controllers.SwanController.testUnregisterThingSpeakValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/thingspeaktest/value""", """controllers.SwanController.testRegisterThingSpeakTestValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/thingspeaktest/value""", """controllers.SwanController.testUnregisterThingSpeakTestValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/twitter/value""", """controllers.SwanController.testRegisterTwitterValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/twitter/value""", """controllers.SwanController.testUnregisterTwitterValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/twitter/tristate""", """controllers.SwanController.testRegisterTwitterTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/twitter/tristate""", """controllers.SwanController.testUnregisterTwitterTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/thingspeaktest/tristate""", """controllers.SwanController.testRegisterThingSpeakTestTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/thingspeaktest/tristate""", """controllers.SwanController.testUnregisterThingSpeakTestTriStateSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/guardian/value""", """controllers.SwanController.testRegisterGuardianValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/guardian/value""", """controllers.SwanController.testUnregisterGuardianValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sensors""", """controllers.SwanController.testGetAllSensors"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/sound/expression""", """controllers.SwanController.registerExpression"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/fog/expression""", """controllers.SwanController.registerFogExpression"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/fog/sensor""", """controllers.SwanController.registerSensor"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/fog/request/""", """controllers.FogController.requestToFog"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/fog/receive/""", """controllers.FogController.receiveFromFog"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/fog/test/""", """controllers.FogController.testFogSensor"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/fog/generate/""", """controllers.FogController.generateFogSensor"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/fog/publish/""", """controllers.FogController.publishExpression"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/fog/subscribe/""", """controllers.FogController.subscribeExpression"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/test/send/""", """controllers.SwanController.sendDataToPhone"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/test/register/""", """controllers.SwanController.swanPhoneTestRegister"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swan/test/unregister/""", """controllers.SwanController.swanPhoneTestUnregister"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ws/""", """controllers.WebSocketController.socket"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register/websocket/value""", """controllers.SwanController.testRegisterWebSocketValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """unregister/websocket/value""", """controllers.SwanController.testUnregisterWebSocketValueSwan"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEAN/""", """controllers.WebSocketController.computationTest_MEAN"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEDIAN/""", """controllers.WebSocketController.computationTest_MEDIAN"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEAN_ON2/""", """controllers.WebSocketController.computationTest_MEAN_ON2"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEAN_ON3/""", """controllers.WebSocketController.computationTest_MEAN_ON3"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEAN_PARALLEL/""", """controllers.WebSocketController.computationTest_MEAN_PARALLEL"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEAN_ON2_PARALLEL/""", """controllers.WebSocketController.computationTest_MEAN_ON2_PARALLEL"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kea/test/MEAN_ON3_PARALLEL/""", """controllers.WebSocketController.computationTest_MEAN_ON3_PARALLEL"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_SwanController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_SwanController_index0_invoker = createInvoker(
    SwanController_0.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_SwanController_swanSongFormService1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swansong")))
  )
  private[this] lazy val controllers_SwanController_swanSongFormService1_invoker = createInvoker(
    SwanController_0.swanSongFormService,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "swanSongFormService",
      Nil,
      "POST",
      """""",
      this.prefix + """swansong"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_SwanController_swanSongJSONService2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swansongjson")))
  )
  private[this] lazy val controllers_SwanController_swanSongJSONService2_invoker = createInvoker(
    SwanController_0.swanSongJSONService,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "swanSongJSONService",
      Nil,
      "POST",
      """""",
      this.prefix + """swansongjson"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_SwanController_testWebService3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("push")))
  )
  private[this] lazy val controllers_SwanController_testWebService3_invoker = createInvoker(
    SwanController_0.testWebService,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testWebService",
      Nil,
      "GET",
      """""",
      this.prefix + """push"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_SwanController_testRegisterRainValueSwan4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registerrainvalue")))
  )
  private[this] lazy val controllers_SwanController_testRegisterRainValueSwan4_invoker = createInvoker(
    SwanController_0.testRegisterRainValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterRainValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """registerrainvalue"""
    )
  )

  // @LINE:19
  private[this] lazy val controllers_SwanController_testUnregisterRainValueSwan5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregisterrainvalue")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterRainValueSwan5_invoker = createInvoker(
    SwanController_0.testUnregisterRainValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterRainValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregisterrainvalue"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_SwanController_testRegisterRainTriStateSwan6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registerraintristate")))
  )
  private[this] lazy val controllers_SwanController_testRegisterRainTriStateSwan6_invoker = createInvoker(
    SwanController_0.testRegisterRainTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterRainTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """registerraintristate"""
    )
  )

  // @LINE:25
  private[this] lazy val controllers_SwanController_testUnregisterRainTriStateSwan7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregisterraintristate")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterRainTriStateSwan7_invoker = createInvoker(
    SwanController_0.testUnregisterRainTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterRainTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregisterraintristate"""
    )
  )

  // @LINE:28
  private[this] lazy val controllers_SwanController_testRegisterTestValueSwan8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/test/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterTestValueSwan8_invoker = createInvoker(
    SwanController_0.testRegisterTestValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterTestValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/test/value"""
    )
  )

  // @LINE:30
  private[this] lazy val controllers_SwanController_testUnregisterTestValueSwan9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/test/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterTestValueSwan9_invoker = createInvoker(
    SwanController_0.testUnregisterTestValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterTestValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/test/value"""
    )
  )

  // @LINE:33
  private[this] lazy val controllers_SwanController_testRegisterTestTriStateSwan10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registertesttristate")))
  )
  private[this] lazy val controllers_SwanController_testRegisterTestTriStateSwan10_invoker = createInvoker(
    SwanController_0.testRegisterTestTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterTestTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """registertesttristate"""
    )
  )

  // @LINE:36
  private[this] lazy val controllers_SwanController_testUnregisterTestTriStateSwan11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregistertesttristate")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterTestTriStateSwan11_invoker = createInvoker(
    SwanController_0.testUnregisterTestTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterTestTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregistertesttristate"""
    )
  )

  // @LINE:39
  private[this] lazy val controllers_SwanController_testSVEFlink12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registerSVE")))
  )
  private[this] lazy val controllers_SwanController_testSVEFlink12_invoker = createInvoker(
    SwanController_0.testSVEFlink,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testSVEFlink",
      Nil,
      "GET",
      """""",
      this.prefix + """registerSVE"""
    )
  )

  // @LINE:42
  private[this] lazy val controllers_SwanController_testSound13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("testSound")))
  )
  private[this] lazy val controllers_SwanController_testSound13_invoker = createInvoker(
    SwanController_0.testSound,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testSound",
      Nil,
      "GET",
      """""",
      this.prefix + """testSound"""
    )
  )

  // @LINE:45
  private[this] lazy val controllers_SwanController_testSound_local14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("testSoundLocal")))
  )
  private[this] lazy val controllers_SwanController_testSound_local14_invoker = createInvoker(
    SwanController_0.testSound_local,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testSound_local",
      Nil,
      "GET",
      """""",
      this.prefix + """testSoundLocal"""
    )
  )

  // @LINE:49
  private[this] lazy val controllers_SwanController_testRegisterCurrencyValueSwan15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registercurrencyvalue")))
  )
  private[this] lazy val controllers_SwanController_testRegisterCurrencyValueSwan15_invoker = createInvoker(
    SwanController_0.testRegisterCurrencyValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterCurrencyValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """registercurrencyvalue"""
    )
  )

  // @LINE:51
  private[this] lazy val controllers_SwanController_testUnregisterCurrencyValueSwan16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregistercurrencyvalue")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterCurrencyValueSwan16_invoker = createInvoker(
    SwanController_0.testUnregisterCurrencyValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterCurrencyValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregistercurrencyvalue"""
    )
  )

  // @LINE:54
  private[this] lazy val controllers_SwanController_testRegisterCurrencyTriStateSwan17_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registercurrencytristate")))
  )
  private[this] lazy val controllers_SwanController_testRegisterCurrencyTriStateSwan17_invoker = createInvoker(
    SwanController_0.testRegisterCurrencyTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterCurrencyTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """registercurrencytristate"""
    )
  )

  // @LINE:57
  private[this] lazy val controllers_SwanController_testUnregisterCurrencyTriStateSwan18_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregistercurrencytristate")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterCurrencyTriStateSwan18_invoker = createInvoker(
    SwanController_0.testUnregisterCurrencyTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterCurrencyTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregistercurrencytristate"""
    )
  )

  // @LINE:60
  private[this] lazy val controllers_SwanController_stop19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("stop")))
  )
  private[this] lazy val controllers_SwanController_stop19_invoker = createInvoker(
    SwanController_0.stop,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "stop",
      Nil,
      "GET",
      """""",
      this.prefix + """stop"""
    )
  )

  // @LINE:66
  private[this] lazy val controllers_SwanController_testRegisterAll20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registerall")))
  )
  private[this] lazy val controllers_SwanController_testRegisterAll20_invoker = createInvoker(
    SwanController_0.testRegisterAll,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterAll",
      Nil,
      "GET",
      """""",
      this.prefix + """registerall"""
    )
  )

  // @LINE:68
  private[this] lazy val controllers_SwanController_testUnregisterAll21_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregisterall")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterAll21_invoker = createInvoker(
    SwanController_0.testUnregisterAll,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterAll",
      Nil,
      "GET",
      """""",
      this.prefix + """unregisterall"""
    )
  )

  // @LINE:71
  private[this] lazy val controllers_SwanController_registerExpressionForEmailNotification22_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registeremail")))
  )
  private[this] lazy val controllers_SwanController_registerExpressionForEmailNotification22_invoker = createInvoker(
    SwanController_0.registerExpressionForEmailNotification,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "registerExpressionForEmailNotification",
      Nil,
      "POST",
      """""",
      this.prefix + """registeremail"""
    )
  )

  // @LINE:73
  private[this] lazy val controllers_SwanController_unRegisterExpressionForEmailNotification23_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregisteremail")))
  )
  private[this] lazy val controllers_SwanController_unRegisterExpressionForEmailNotification23_invoker = createInvoker(
    SwanController_0.unRegisterExpressionForEmailNotification,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "unRegisterExpressionForEmailNotification",
      Nil,
      "POST",
      """""",
      this.prefix + """unregisteremail"""
    )
  )

  // @LINE:76
  private[this] lazy val controllers_SwanController_setupWebHook24_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("webhook/")))
  )
  private[this] lazy val controllers_SwanController_setupWebHook24_invoker = createInvoker(
    SwanController_0.setupWebHook,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "setupWebHook",
      Nil,
      "GET",
      """""",
      this.prefix + """webhook/"""
    )
  )

  // @LINE:79
  private[this] lazy val controllers_SwanController_requestWebHookSwanBot25_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("webhook/")))
  )
  private[this] lazy val controllers_SwanController_requestWebHookSwanBot25_invoker = createInvoker(
    SwanController_0.requestWebHookSwanBot,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "requestWebHookSwanBot",
      Nil,
      "POST",
      """""",
      this.prefix + """webhook/"""
    )
  )

  // @LINE:82
  private[this] lazy val controllers_SwanController_swanPhoneRegister26_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/register/")))
  )
  private[this] lazy val controllers_SwanController_swanPhoneRegister26_invoker = createInvoker(
    SwanController_0.swanPhoneRegister,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "swanPhoneRegister",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/register/"""
    )
  )

  // @LINE:84
  private[this] lazy val controllers_SwanController_swanPhoneUnregister27_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/unregister/")))
  )
  private[this] lazy val controllers_SwanController_swanPhoneUnregister27_invoker = createInvoker(
    SwanController_0.swanPhoneUnregister,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "swanPhoneUnregister",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/unregister/"""
    )
  )

  // @LINE:88
  private[this] lazy val controllers_SwanController_testRegisterLoraValueSwan28_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/lora/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterLoraValueSwan28_invoker = createInvoker(
    SwanController_0.testRegisterLoraValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterLoraValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/lora/value"""
    )
  )

  // @LINE:90
  private[this] lazy val controllers_SwanController_testUnregisterLoraValueSwan29_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/lora/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterLoraValueSwan29_invoker = createInvoker(
    SwanController_0.testUnregisterLoraValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterLoraValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/lora/value"""
    )
  )

  // @LINE:92
  private[this] lazy val controllers_SwanController_testRegisterThingSpeakValueSwan30_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/thingspeak/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterThingSpeakValueSwan30_invoker = createInvoker(
    SwanController_0.testRegisterThingSpeakValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterThingSpeakValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/thingspeak/value"""
    )
  )

  // @LINE:94
  private[this] lazy val controllers_SwanController_testUnregisterThingSpeakValueSwan31_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/thingspeak/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterThingSpeakValueSwan31_invoker = createInvoker(
    SwanController_0.testUnregisterThingSpeakValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterThingSpeakValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/thingspeak/value"""
    )
  )

  // @LINE:96
  private[this] lazy val controllers_SwanController_testRegisterThingSpeakTestValueSwan32_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/thingspeaktest/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterThingSpeakTestValueSwan32_invoker = createInvoker(
    SwanController_0.testRegisterThingSpeakTestValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterThingSpeakTestValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/thingspeaktest/value"""
    )
  )

  // @LINE:98
  private[this] lazy val controllers_SwanController_testUnregisterThingSpeakTestValueSwan33_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/thingspeaktest/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterThingSpeakTestValueSwan33_invoker = createInvoker(
    SwanController_0.testUnregisterThingSpeakTestValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterThingSpeakTestValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/thingspeaktest/value"""
    )
  )

  // @LINE:100
  private[this] lazy val controllers_SwanController_testRegisterTwitterValueSwan34_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/twitter/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterTwitterValueSwan34_invoker = createInvoker(
    SwanController_0.testRegisterTwitterValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterTwitterValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/twitter/value"""
    )
  )

  // @LINE:102
  private[this] lazy val controllers_SwanController_testUnregisterTwitterValueSwan35_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/twitter/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterTwitterValueSwan35_invoker = createInvoker(
    SwanController_0.testUnregisterTwitterValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterTwitterValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/twitter/value"""
    )
  )

  // @LINE:104
  private[this] lazy val controllers_SwanController_testRegisterTwitterTriStateSwan36_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/twitter/tristate")))
  )
  private[this] lazy val controllers_SwanController_testRegisterTwitterTriStateSwan36_invoker = createInvoker(
    SwanController_0.testRegisterTwitterTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterTwitterTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/twitter/tristate"""
    )
  )

  // @LINE:106
  private[this] lazy val controllers_SwanController_testUnregisterTwitterTriStateSwan37_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/twitter/tristate")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterTwitterTriStateSwan37_invoker = createInvoker(
    SwanController_0.testUnregisterTwitterTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterTwitterTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/twitter/tristate"""
    )
  )

  // @LINE:108
  private[this] lazy val controllers_SwanController_testRegisterThingSpeakTestTriStateSwan38_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/thingspeaktest/tristate")))
  )
  private[this] lazy val controllers_SwanController_testRegisterThingSpeakTestTriStateSwan38_invoker = createInvoker(
    SwanController_0.testRegisterThingSpeakTestTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterThingSpeakTestTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/thingspeaktest/tristate"""
    )
  )

  // @LINE:110
  private[this] lazy val controllers_SwanController_testUnregisterThingSpeakTestTriStateSwan39_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/thingspeaktest/tristate")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterThingSpeakTestTriStateSwan39_invoker = createInvoker(
    SwanController_0.testUnregisterThingSpeakTestTriStateSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterThingSpeakTestTriStateSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/thingspeaktest/tristate"""
    )
  )

  // @LINE:112
  private[this] lazy val controllers_SwanController_testRegisterGuardianValueSwan40_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/guardian/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterGuardianValueSwan40_invoker = createInvoker(
    SwanController_0.testRegisterGuardianValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterGuardianValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/guardian/value"""
    )
  )

  // @LINE:114
  private[this] lazy val controllers_SwanController_testUnregisterGuardianValueSwan41_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/guardian/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterGuardianValueSwan41_invoker = createInvoker(
    SwanController_0.testUnregisterGuardianValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterGuardianValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/guardian/value"""
    )
  )

  // @LINE:117
  private[this] lazy val controllers_SwanController_testGetAllSensors42_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sensors")))
  )
  private[this] lazy val controllers_SwanController_testGetAllSensors42_invoker = createInvoker(
    SwanController_0.testGetAllSensors,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testGetAllSensors",
      Nil,
      "GET",
      """""",
      this.prefix + """sensors"""
    )
  )

  // @LINE:119
  private[this] lazy val controllers_SwanController_registerExpression43_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/sound/expression")))
  )
  private[this] lazy val controllers_SwanController_registerExpression43_invoker = createInvoker(
    SwanController_0.registerExpression,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "registerExpression",
      Nil,
      "POST",
      """""",
      this.prefix + """register/sound/expression"""
    )
  )

  // @LINE:121
  private[this] lazy val controllers_SwanController_registerFogExpression44_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/fog/expression")))
  )
  private[this] lazy val controllers_SwanController_registerFogExpression44_invoker = createInvoker(
    SwanController_0.registerFogExpression,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "registerFogExpression",
      Nil,
      "GET",
      """""",
      this.prefix + """register/fog/expression"""
    )
  )

  // @LINE:123
  private[this] lazy val controllers_SwanController_registerSensor45_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/fog/sensor")))
  )
  private[this] lazy val controllers_SwanController_registerSensor45_invoker = createInvoker(
    SwanController_0.registerSensor,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "registerSensor",
      Nil,
      "GET",
      """""",
      this.prefix + """register/fog/sensor"""
    )
  )

  // @LINE:125
  private[this] lazy val controllers_FogController_requestToFog46_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/fog/request/")))
  )
  private[this] lazy val controllers_FogController_requestToFog46_invoker = createInvoker(
    FogController_1.requestToFog,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FogController",
      "requestToFog",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/fog/request/"""
    )
  )

  // @LINE:127
  private[this] lazy val controllers_FogController_receiveFromFog47_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/fog/receive/")))
  )
  private[this] lazy val controllers_FogController_receiveFromFog47_invoker = createInvoker(
    FogController_1.receiveFromFog,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FogController",
      "receiveFromFog",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/fog/receive/"""
    )
  )

  // @LINE:129
  private[this] lazy val controllers_FogController_testFogSensor48_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/fog/test/")))
  )
  private[this] lazy val controllers_FogController_testFogSensor48_invoker = createInvoker(
    FogController_1.testFogSensor,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FogController",
      "testFogSensor",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/fog/test/"""
    )
  )

  // @LINE:131
  private[this] lazy val controllers_FogController_generateFogSensor49_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/fog/generate/")))
  )
  private[this] lazy val controllers_FogController_generateFogSensor49_invoker = createInvoker(
    FogController_1.generateFogSensor,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FogController",
      "generateFogSensor",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/fog/generate/"""
    )
  )

  // @LINE:134
  private[this] lazy val controllers_FogController_publishExpression50_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/fog/publish/")))
  )
  private[this] lazy val controllers_FogController_publishExpression50_invoker = createInvoker(
    FogController_1.publishExpression,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FogController",
      "publishExpression",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/fog/publish/"""
    )
  )

  // @LINE:135
  private[this] lazy val controllers_FogController_subscribeExpression51_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/fog/subscribe/")))
  )
  private[this] lazy val controllers_FogController_subscribeExpression51_invoker = createInvoker(
    FogController_1.subscribeExpression,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FogController",
      "subscribeExpression",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/fog/subscribe/"""
    )
  )

  // @LINE:137
  private[this] lazy val controllers_SwanController_sendDataToPhone52_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/test/send/")))
  )
  private[this] lazy val controllers_SwanController_sendDataToPhone52_invoker = createInvoker(
    SwanController_0.sendDataToPhone,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "sendDataToPhone",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/test/send/"""
    )
  )

  // @LINE:139
  private[this] lazy val controllers_SwanController_swanPhoneTestRegister53_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/test/register/")))
  )
  private[this] lazy val controllers_SwanController_swanPhoneTestRegister53_invoker = createInvoker(
    SwanController_0.swanPhoneTestRegister,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "swanPhoneTestRegister",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/test/register/"""
    )
  )

  // @LINE:140
  private[this] lazy val controllers_SwanController_swanPhoneTestUnregister54_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swan/test/unregister/")))
  )
  private[this] lazy val controllers_SwanController_swanPhoneTestUnregister54_invoker = createInvoker(
    SwanController_0.swanPhoneTestUnregister,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "swanPhoneTestUnregister",
      Nil,
      "POST",
      """""",
      this.prefix + """swan/test/unregister/"""
    )
  )

  // @LINE:143
  private[this] lazy val controllers_WebSocketController_socket55_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ws/")))
  )
  private[this] lazy val controllers_WebSocketController_socket55_invoker = createInvoker(
    WebSocketController_2.socket,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "socket",
      Nil,
      "GET",
      """""",
      this.prefix + """ws/"""
    )
  )

  // @LINE:146
  private[this] lazy val controllers_SwanController_testRegisterWebSocketValueSwan56_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register/websocket/value")))
  )
  private[this] lazy val controllers_SwanController_testRegisterWebSocketValueSwan56_invoker = createInvoker(
    SwanController_0.testRegisterWebSocketValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testRegisterWebSocketValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """register/websocket/value"""
    )
  )

  // @LINE:148
  private[this] lazy val controllers_SwanController_testUnregisterWebSocketValueSwan57_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unregister/websocket/value")))
  )
  private[this] lazy val controllers_SwanController_testUnregisterWebSocketValueSwan57_invoker = createInvoker(
    SwanController_0.testUnregisterWebSocketValueSwan,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwanController",
      "testUnregisterWebSocketValueSwan",
      Nil,
      "GET",
      """""",
      this.prefix + """unregister/websocket/value"""
    )
  )

  // @LINE:152
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN58_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEAN/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN58_invoker = createInvoker(
    WebSocketController_2.computationTest_MEAN,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEAN",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEAN/"""
    )
  )

  // @LINE:153
  private[this] lazy val controllers_WebSocketController_computationTest_MEDIAN59_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEDIAN/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEDIAN59_invoker = createInvoker(
    WebSocketController_2.computationTest_MEDIAN,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEDIAN",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEDIAN/"""
    )
  )

  // @LINE:154
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON260_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEAN_ON2/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON260_invoker = createInvoker(
    WebSocketController_2.computationTest_MEAN_ON2,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEAN_ON2",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEAN_ON2/"""
    )
  )

  // @LINE:155
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON361_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEAN_ON3/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON361_invoker = createInvoker(
    WebSocketController_2.computationTest_MEAN_ON3,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEAN_ON3",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEAN_ON3/"""
    )
  )

  // @LINE:156
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_PARALLEL62_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEAN_PARALLEL/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_PARALLEL62_invoker = createInvoker(
    WebSocketController_2.computationTest_MEAN_PARALLEL,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEAN_PARALLEL",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEAN_PARALLEL/"""
    )
  )

  // @LINE:157
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON2_PARALLEL63_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEAN_ON2_PARALLEL/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON2_PARALLEL63_invoker = createInvoker(
    WebSocketController_2.computationTest_MEAN_ON2_PARALLEL,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEAN_ON2_PARALLEL",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEAN_ON2_PARALLEL/"""
    )
  )

  // @LINE:158
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON3_PARALLEL64_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kea/test/MEAN_ON3_PARALLEL/")))
  )
  private[this] lazy val controllers_WebSocketController_computationTest_MEAN_ON3_PARALLEL64_invoker = createInvoker(
    WebSocketController_2.computationTest_MEAN_ON3_PARALLEL,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "computationTest_MEAN_ON3_PARALLEL",
      Nil,
      "GET",
      """""",
      this.prefix + """kea/test/MEAN_ON3_PARALLEL/"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_SwanController_index0_route(params) =>
      call { 
        controllers_SwanController_index0_invoker.call(SwanController_0.index)
      }
  
    // @LINE:11
    case controllers_SwanController_swanSongFormService1_route(params) =>
      call { 
        controllers_SwanController_swanSongFormService1_invoker.call(SwanController_0.swanSongFormService)
      }
  
    // @LINE:13
    case controllers_SwanController_swanSongJSONService2_route(params) =>
      call { 
        controllers_SwanController_swanSongJSONService2_invoker.call(SwanController_0.swanSongJSONService)
      }
  
    // @LINE:15
    case controllers_SwanController_testWebService3_route(params) =>
      call { 
        controllers_SwanController_testWebService3_invoker.call(SwanController_0.testWebService)
      }
  
    // @LINE:17
    case controllers_SwanController_testRegisterRainValueSwan4_route(params) =>
      call { 
        controllers_SwanController_testRegisterRainValueSwan4_invoker.call(SwanController_0.testRegisterRainValueSwan)
      }
  
    // @LINE:19
    case controllers_SwanController_testUnregisterRainValueSwan5_route(params) =>
      call { 
        controllers_SwanController_testUnregisterRainValueSwan5_invoker.call(SwanController_0.testUnregisterRainValueSwan)
      }
  
    // @LINE:22
    case controllers_SwanController_testRegisterRainTriStateSwan6_route(params) =>
      call { 
        controllers_SwanController_testRegisterRainTriStateSwan6_invoker.call(SwanController_0.testRegisterRainTriStateSwan)
      }
  
    // @LINE:25
    case controllers_SwanController_testUnregisterRainTriStateSwan7_route(params) =>
      call { 
        controllers_SwanController_testUnregisterRainTriStateSwan7_invoker.call(SwanController_0.testUnregisterRainTriStateSwan)
      }
  
    // @LINE:28
    case controllers_SwanController_testRegisterTestValueSwan8_route(params) =>
      call { 
        controllers_SwanController_testRegisterTestValueSwan8_invoker.call(SwanController_0.testRegisterTestValueSwan)
      }
  
    // @LINE:30
    case controllers_SwanController_testUnregisterTestValueSwan9_route(params) =>
      call { 
        controllers_SwanController_testUnregisterTestValueSwan9_invoker.call(SwanController_0.testUnregisterTestValueSwan)
      }
  
    // @LINE:33
    case controllers_SwanController_testRegisterTestTriStateSwan10_route(params) =>
      call { 
        controllers_SwanController_testRegisterTestTriStateSwan10_invoker.call(SwanController_0.testRegisterTestTriStateSwan)
      }
  
    // @LINE:36
    case controllers_SwanController_testUnregisterTestTriStateSwan11_route(params) =>
      call { 
        controllers_SwanController_testUnregisterTestTriStateSwan11_invoker.call(SwanController_0.testUnregisterTestTriStateSwan)
      }
  
    // @LINE:39
    case controllers_SwanController_testSVEFlink12_route(params) =>
      call { 
        controllers_SwanController_testSVEFlink12_invoker.call(SwanController_0.testSVEFlink)
      }
  
    // @LINE:42
    case controllers_SwanController_testSound13_route(params) =>
      call { 
        controllers_SwanController_testSound13_invoker.call(SwanController_0.testSound)
      }
  
    // @LINE:45
    case controllers_SwanController_testSound_local14_route(params) =>
      call { 
        controllers_SwanController_testSound_local14_invoker.call(SwanController_0.testSound_local)
      }
  
    // @LINE:49
    case controllers_SwanController_testRegisterCurrencyValueSwan15_route(params) =>
      call { 
        controllers_SwanController_testRegisterCurrencyValueSwan15_invoker.call(SwanController_0.testRegisterCurrencyValueSwan)
      }
  
    // @LINE:51
    case controllers_SwanController_testUnregisterCurrencyValueSwan16_route(params) =>
      call { 
        controllers_SwanController_testUnregisterCurrencyValueSwan16_invoker.call(SwanController_0.testUnregisterCurrencyValueSwan)
      }
  
    // @LINE:54
    case controllers_SwanController_testRegisterCurrencyTriStateSwan17_route(params) =>
      call { 
        controllers_SwanController_testRegisterCurrencyTriStateSwan17_invoker.call(SwanController_0.testRegisterCurrencyTriStateSwan)
      }
  
    // @LINE:57
    case controllers_SwanController_testUnregisterCurrencyTriStateSwan18_route(params) =>
      call { 
        controllers_SwanController_testUnregisterCurrencyTriStateSwan18_invoker.call(SwanController_0.testUnregisterCurrencyTriStateSwan)
      }
  
    // @LINE:60
    case controllers_SwanController_stop19_route(params) =>
      call { 
        controllers_SwanController_stop19_invoker.call(SwanController_0.stop)
      }
  
    // @LINE:66
    case controllers_SwanController_testRegisterAll20_route(params) =>
      call { 
        controllers_SwanController_testRegisterAll20_invoker.call(SwanController_0.testRegisterAll)
      }
  
    // @LINE:68
    case controllers_SwanController_testUnregisterAll21_route(params) =>
      call { 
        controllers_SwanController_testUnregisterAll21_invoker.call(SwanController_0.testUnregisterAll)
      }
  
    // @LINE:71
    case controllers_SwanController_registerExpressionForEmailNotification22_route(params) =>
      call { 
        controllers_SwanController_registerExpressionForEmailNotification22_invoker.call(SwanController_0.registerExpressionForEmailNotification)
      }
  
    // @LINE:73
    case controllers_SwanController_unRegisterExpressionForEmailNotification23_route(params) =>
      call { 
        controllers_SwanController_unRegisterExpressionForEmailNotification23_invoker.call(SwanController_0.unRegisterExpressionForEmailNotification)
      }
  
    // @LINE:76
    case controllers_SwanController_setupWebHook24_route(params) =>
      call { 
        controllers_SwanController_setupWebHook24_invoker.call(SwanController_0.setupWebHook)
      }
  
    // @LINE:79
    case controllers_SwanController_requestWebHookSwanBot25_route(params) =>
      call { 
        controllers_SwanController_requestWebHookSwanBot25_invoker.call(SwanController_0.requestWebHookSwanBot)
      }
  
    // @LINE:82
    case controllers_SwanController_swanPhoneRegister26_route(params) =>
      call { 
        controllers_SwanController_swanPhoneRegister26_invoker.call(SwanController_0.swanPhoneRegister)
      }
  
    // @LINE:84
    case controllers_SwanController_swanPhoneUnregister27_route(params) =>
      call { 
        controllers_SwanController_swanPhoneUnregister27_invoker.call(SwanController_0.swanPhoneUnregister)
      }
  
    // @LINE:88
    case controllers_SwanController_testRegisterLoraValueSwan28_route(params) =>
      call { 
        controllers_SwanController_testRegisterLoraValueSwan28_invoker.call(SwanController_0.testRegisterLoraValueSwan)
      }
  
    // @LINE:90
    case controllers_SwanController_testUnregisterLoraValueSwan29_route(params) =>
      call { 
        controllers_SwanController_testUnregisterLoraValueSwan29_invoker.call(SwanController_0.testUnregisterLoraValueSwan)
      }
  
    // @LINE:92
    case controllers_SwanController_testRegisterThingSpeakValueSwan30_route(params) =>
      call { 
        controllers_SwanController_testRegisterThingSpeakValueSwan30_invoker.call(SwanController_0.testRegisterThingSpeakValueSwan)
      }
  
    // @LINE:94
    case controllers_SwanController_testUnregisterThingSpeakValueSwan31_route(params) =>
      call { 
        controllers_SwanController_testUnregisterThingSpeakValueSwan31_invoker.call(SwanController_0.testUnregisterThingSpeakValueSwan)
      }
  
    // @LINE:96
    case controllers_SwanController_testRegisterThingSpeakTestValueSwan32_route(params) =>
      call { 
        controllers_SwanController_testRegisterThingSpeakTestValueSwan32_invoker.call(SwanController_0.testRegisterThingSpeakTestValueSwan)
      }
  
    // @LINE:98
    case controllers_SwanController_testUnregisterThingSpeakTestValueSwan33_route(params) =>
      call { 
        controllers_SwanController_testUnregisterThingSpeakTestValueSwan33_invoker.call(SwanController_0.testUnregisterThingSpeakTestValueSwan)
      }
  
    // @LINE:100
    case controllers_SwanController_testRegisterTwitterValueSwan34_route(params) =>
      call { 
        controllers_SwanController_testRegisterTwitterValueSwan34_invoker.call(SwanController_0.testRegisterTwitterValueSwan)
      }
  
    // @LINE:102
    case controllers_SwanController_testUnregisterTwitterValueSwan35_route(params) =>
      call { 
        controllers_SwanController_testUnregisterTwitterValueSwan35_invoker.call(SwanController_0.testUnregisterTwitterValueSwan)
      }
  
    // @LINE:104
    case controllers_SwanController_testRegisterTwitterTriStateSwan36_route(params) =>
      call { 
        controllers_SwanController_testRegisterTwitterTriStateSwan36_invoker.call(SwanController_0.testRegisterTwitterTriStateSwan)
      }
  
    // @LINE:106
    case controllers_SwanController_testUnregisterTwitterTriStateSwan37_route(params) =>
      call { 
        controllers_SwanController_testUnregisterTwitterTriStateSwan37_invoker.call(SwanController_0.testUnregisterTwitterTriStateSwan)
      }
  
    // @LINE:108
    case controllers_SwanController_testRegisterThingSpeakTestTriStateSwan38_route(params) =>
      call { 
        controllers_SwanController_testRegisterThingSpeakTestTriStateSwan38_invoker.call(SwanController_0.testRegisterThingSpeakTestTriStateSwan)
      }
  
    // @LINE:110
    case controllers_SwanController_testUnregisterThingSpeakTestTriStateSwan39_route(params) =>
      call { 
        controllers_SwanController_testUnregisterThingSpeakTestTriStateSwan39_invoker.call(SwanController_0.testUnregisterThingSpeakTestTriStateSwan)
      }
  
    // @LINE:112
    case controllers_SwanController_testRegisterGuardianValueSwan40_route(params) =>
      call { 
        controllers_SwanController_testRegisterGuardianValueSwan40_invoker.call(SwanController_0.testRegisterGuardianValueSwan)
      }
  
    // @LINE:114
    case controllers_SwanController_testUnregisterGuardianValueSwan41_route(params) =>
      call { 
        controllers_SwanController_testUnregisterGuardianValueSwan41_invoker.call(SwanController_0.testUnregisterGuardianValueSwan)
      }
  
    // @LINE:117
    case controllers_SwanController_testGetAllSensors42_route(params) =>
      call { 
        controllers_SwanController_testGetAllSensors42_invoker.call(SwanController_0.testGetAllSensors)
      }
  
    // @LINE:119
    case controllers_SwanController_registerExpression43_route(params) =>
      call { 
        controllers_SwanController_registerExpression43_invoker.call(SwanController_0.registerExpression)
      }
  
    // @LINE:121
    case controllers_SwanController_registerFogExpression44_route(params) =>
      call { 
        controllers_SwanController_registerFogExpression44_invoker.call(SwanController_0.registerFogExpression)
      }
  
    // @LINE:123
    case controllers_SwanController_registerSensor45_route(params) =>
      call { 
        controllers_SwanController_registerSensor45_invoker.call(SwanController_0.registerSensor)
      }
  
    // @LINE:125
    case controllers_FogController_requestToFog46_route(params) =>
      call { 
        controllers_FogController_requestToFog46_invoker.call(FogController_1.requestToFog)
      }
  
    // @LINE:127
    case controllers_FogController_receiveFromFog47_route(params) =>
      call { 
        controllers_FogController_receiveFromFog47_invoker.call(FogController_1.receiveFromFog)
      }
  
    // @LINE:129
    case controllers_FogController_testFogSensor48_route(params) =>
      call { 
        controllers_FogController_testFogSensor48_invoker.call(FogController_1.testFogSensor)
      }
  
    // @LINE:131
    case controllers_FogController_generateFogSensor49_route(params) =>
      call { 
        controllers_FogController_generateFogSensor49_invoker.call(FogController_1.generateFogSensor)
      }
  
    // @LINE:134
    case controllers_FogController_publishExpression50_route(params) =>
      call { 
        controllers_FogController_publishExpression50_invoker.call(FogController_1.publishExpression)
      }
  
    // @LINE:135
    case controllers_FogController_subscribeExpression51_route(params) =>
      call { 
        controllers_FogController_subscribeExpression51_invoker.call(FogController_1.subscribeExpression)
      }
  
    // @LINE:137
    case controllers_SwanController_sendDataToPhone52_route(params) =>
      call { 
        controllers_SwanController_sendDataToPhone52_invoker.call(SwanController_0.sendDataToPhone)
      }
  
    // @LINE:139
    case controllers_SwanController_swanPhoneTestRegister53_route(params) =>
      call { 
        controllers_SwanController_swanPhoneTestRegister53_invoker.call(SwanController_0.swanPhoneTestRegister)
      }
  
    // @LINE:140
    case controllers_SwanController_swanPhoneTestUnregister54_route(params) =>
      call { 
        controllers_SwanController_swanPhoneTestUnregister54_invoker.call(SwanController_0.swanPhoneTestUnregister)
      }
  
    // @LINE:143
    case controllers_WebSocketController_socket55_route(params) =>
      call { 
        controllers_WebSocketController_socket55_invoker.call(WebSocketController_2.socket)
      }
  
    // @LINE:146
    case controllers_SwanController_testRegisterWebSocketValueSwan56_route(params) =>
      call { 
        controllers_SwanController_testRegisterWebSocketValueSwan56_invoker.call(SwanController_0.testRegisterWebSocketValueSwan)
      }
  
    // @LINE:148
    case controllers_SwanController_testUnregisterWebSocketValueSwan57_route(params) =>
      call { 
        controllers_SwanController_testUnregisterWebSocketValueSwan57_invoker.call(SwanController_0.testUnregisterWebSocketValueSwan)
      }
  
    // @LINE:152
    case controllers_WebSocketController_computationTest_MEAN58_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEAN58_invoker.call(WebSocketController_2.computationTest_MEAN)
      }
  
    // @LINE:153
    case controllers_WebSocketController_computationTest_MEDIAN59_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEDIAN59_invoker.call(WebSocketController_2.computationTest_MEDIAN)
      }
  
    // @LINE:154
    case controllers_WebSocketController_computationTest_MEAN_ON260_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEAN_ON260_invoker.call(WebSocketController_2.computationTest_MEAN_ON2)
      }
  
    // @LINE:155
    case controllers_WebSocketController_computationTest_MEAN_ON361_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEAN_ON361_invoker.call(WebSocketController_2.computationTest_MEAN_ON3)
      }
  
    // @LINE:156
    case controllers_WebSocketController_computationTest_MEAN_PARALLEL62_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEAN_PARALLEL62_invoker.call(WebSocketController_2.computationTest_MEAN_PARALLEL)
      }
  
    // @LINE:157
    case controllers_WebSocketController_computationTest_MEAN_ON2_PARALLEL63_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEAN_ON2_PARALLEL63_invoker.call(WebSocketController_2.computationTest_MEAN_ON2_PARALLEL)
      }
  
    // @LINE:158
    case controllers_WebSocketController_computationTest_MEAN_ON3_PARALLEL64_route(params) =>
      call { 
        controllers_WebSocketController_computationTest_MEAN_ON3_PARALLEL64_invoker.call(WebSocketController_2.computationTest_MEAN_ON3_PARALLEL)
      }
  }
}
