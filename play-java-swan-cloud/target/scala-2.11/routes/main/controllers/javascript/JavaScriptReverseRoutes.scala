
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/gdibernardo/Documents/SWAN-paper/cowbird-distributed/play-java-swan-cloud/conf/routes
// @DATE:Mon Nov 20 11:31:40 CET 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:125
  class ReverseFogController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:125
    def requestToFog: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FogController.requestToFog",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/fog/request/"})
        }
      """
    )
  
    // @LINE:127
    def receiveFromFog: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FogController.receiveFromFog",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/fog/receive/"})
        }
      """
    )
  
    // @LINE:131
    def generateFogSensor: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FogController.generateFogSensor",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/fog/generate/"})
        }
      """
    )
  
    // @LINE:134
    def publishExpression: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FogController.publishExpression",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/fog/publish/"})
        }
      """
    )
  
    // @LINE:129
    def testFogSensor: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FogController.testFogSensor",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/fog/test/"})
        }
      """
    )
  
    // @LINE:135
    def subscribeExpression: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FogController.subscribeExpression",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/fog/subscribe/"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseSwanController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:102
    def testUnregisterTwitterValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterTwitterValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/twitter/value"})
        }
      """
    )
  
    // @LINE:57
    def testUnregisterCurrencyTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterCurrencyTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregistercurrencytristate"})
        }
      """
    )
  
    // @LINE:119
    def registerExpression: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.registerExpression",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register/sound/expression"})
        }
      """
    )
  
    // @LINE:140
    def swanPhoneTestUnregister: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.swanPhoneTestUnregister",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/test/unregister/"})
        }
      """
    )
  
    // @LINE:71
    def registerExpressionForEmailNotification: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.registerExpressionForEmailNotification",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "registeremail"})
        }
      """
    )
  
    // @LINE:121
    def registerFogExpression: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.registerFogExpression",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/fog/expression"})
        }
      """
    )
  
    // @LINE:39
    def testSVEFlink: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testSVEFlink",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registerSVE"})
        }
      """
    )
  
    // @LINE:148
    def testUnregisterWebSocketValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterWebSocketValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/websocket/value"})
        }
      """
    )
  
    // @LINE:36
    def testUnregisterTestTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterTestTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregistertesttristate"})
        }
      """
    )
  
    // @LINE:54
    def testRegisterCurrencyTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterCurrencyTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registercurrencytristate"})
        }
      """
    )
  
    // @LINE:98
    def testUnregisterThingSpeakTestValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterThingSpeakTestValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/thingspeaktest/value"})
        }
      """
    )
  
    // @LINE:66
    def testRegisterAll: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterAll",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registerall"})
        }
      """
    )
  
    // @LINE:108
    def testRegisterThingSpeakTestTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterThingSpeakTestTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/thingspeaktest/tristate"})
        }
      """
    )
  
    // @LINE:11
    def swanSongFormService: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.swanSongFormService",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swansong"})
        }
      """
    )
  
    // @LINE:51
    def testUnregisterCurrencyValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterCurrencyValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregistercurrencyvalue"})
        }
      """
    )
  
    // @LINE:96
    def testRegisterThingSpeakTestValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterThingSpeakTestValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/thingspeaktest/value"})
        }
      """
    )
  
    // @LINE:123
    def registerSensor: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.registerSensor",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/fog/sensor"})
        }
      """
    )
  
    // @LINE:30
    def testUnregisterTestValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterTestValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/test/value"})
        }
      """
    )
  
    // @LINE:76
    def setupWebHook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.setupWebHook",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "webhook/"})
        }
      """
    )
  
    // @LINE:104
    def testRegisterTwitterTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterTwitterTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/twitter/tristate"})
        }
      """
    )
  
    // @LINE:90
    def testUnregisterLoraValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterLoraValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/lora/value"})
        }
      """
    )
  
    // @LINE:92
    def testRegisterThingSpeakValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterThingSpeakValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/thingspeak/value"})
        }
      """
    )
  
    // @LINE:139
    def swanPhoneTestRegister: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.swanPhoneTestRegister",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/test/register/"})
        }
      """
    )
  
    // @LINE:112
    def testRegisterGuardianValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterGuardianValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/guardian/value"})
        }
      """
    )
  
    // @LINE:114
    def testUnregisterGuardianValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterGuardianValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/guardian/value"})
        }
      """
    )
  
    // @LINE:88
    def testRegisterLoraValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterLoraValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/lora/value"})
        }
      """
    )
  
    // @LINE:94
    def testUnregisterThingSpeakValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterThingSpeakValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/thingspeak/value"})
        }
      """
    )
  
    // @LINE:45
    def testSound_local: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testSound_local",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "testSoundLocal"})
        }
      """
    )
  
    // @LINE:33
    def testRegisterTestTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterTestTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registertesttristate"})
        }
      """
    )
  
    // @LINE:84
    def swanPhoneUnregister: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.swanPhoneUnregister",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/unregister/"})
        }
      """
    )
  
    // @LINE:82
    def swanPhoneRegister: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.swanPhoneRegister",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/register/"})
        }
      """
    )
  
    // @LINE:137
    def sendDataToPhone: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.sendDataToPhone",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swan/test/send/"})
        }
      """
    )
  
    // @LINE:73
    def unRegisterExpressionForEmailNotification: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.unRegisterExpressionForEmailNotification",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "unregisteremail"})
        }
      """
    )
  
    // @LINE:100
    def testRegisterTwitterValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterTwitterValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/twitter/value"})
        }
      """
    )
  
    // @LINE:117
    def testGetAllSensors: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testGetAllSensors",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sensors"})
        }
      """
    )
  
    // @LINE:19
    def testUnregisterRainValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterRainValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregisterrainvalue"})
        }
      """
    )
  
    // @LINE:22
    def testRegisterRainTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterRainTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registerraintristate"})
        }
      """
    )
  
    // @LINE:28
    def testRegisterTestValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterTestValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/test/value"})
        }
      """
    )
  
    // @LINE:110
    def testUnregisterThingSpeakTestTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterThingSpeakTestTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/thingspeaktest/tristate"})
        }
      """
    )
  
    // @LINE:15
    def testWebService: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testWebService",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "push"})
        }
      """
    )
  
    // @LINE:60
    def stop: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.stop",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "stop"})
        }
      """
    )
  
    // @LINE:146
    def testRegisterWebSocketValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterWebSocketValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register/websocket/value"})
        }
      """
    )
  
    // @LINE:17
    def testRegisterRainValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterRainValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registerrainvalue"})
        }
      """
    )
  
    // @LINE:13
    def swanSongJSONService: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.swanSongJSONService",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "swansongjson"})
        }
      """
    )
  
    // @LINE:79
    def requestWebHookSwanBot: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.requestWebHookSwanBot",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "webhook/"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:49
    def testRegisterCurrencyValueSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testRegisterCurrencyValueSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "registercurrencyvalue"})
        }
      """
    )
  
    // @LINE:68
    def testUnregisterAll: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterAll",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregisterall"})
        }
      """
    )
  
    // @LINE:25
    def testUnregisterRainTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterRainTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregisterraintristate"})
        }
      """
    )
  
    // @LINE:42
    def testSound: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testSound",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "testSound"})
        }
      """
    )
  
    // @LINE:106
    def testUnregisterTwitterTriStateSwan: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwanController.testUnregisterTwitterTriStateSwan",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister/twitter/tristate"})
        }
      """
    )
  
  }

  // @LINE:143
  class ReverseWebSocketController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:153
    def computationTest_MEDIAN: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEDIAN",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEDIAN/"})
        }
      """
    )
  
    // @LINE:154
    def computationTest_MEAN_ON2: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEAN_ON2",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEAN_ON2/"})
        }
      """
    )
  
    // @LINE:158
    def computationTest_MEAN_ON3_PARALLEL: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEAN_ON3_PARALLEL",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEAN_ON3_PARALLEL/"})
        }
      """
    )
  
    // @LINE:155
    def computationTest_MEAN_ON3: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEAN_ON3",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEAN_ON3/"})
        }
      """
    )
  
    // @LINE:143
    def socket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.socket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ws/"})
        }
      """
    )
  
    // @LINE:152
    def computationTest_MEAN: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEAN",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEAN/"})
        }
      """
    )
  
    // @LINE:156
    def computationTest_MEAN_PARALLEL: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEAN_PARALLEL",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEAN_PARALLEL/"})
        }
      """
    )
  
    // @LINE:157
    def computationTest_MEAN_ON2_PARALLEL: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.computationTest_MEAN_ON2_PARALLEL",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kea/test/MEAN_ON2_PARALLEL/"})
        }
      """
    )
  
  }


}
