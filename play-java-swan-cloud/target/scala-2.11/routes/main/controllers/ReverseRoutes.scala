
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/gdibernardo/Documents/SWAN-paper/cowbird-distributed/play-java-swan-cloud/conf/routes
// @DATE:Mon Nov 20 11:31:40 CET 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:125
  class ReverseFogController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:125
    def requestToFog(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/fog/request/")
    }
  
    // @LINE:127
    def receiveFromFog(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/fog/receive/")
    }
  
    // @LINE:131
    def generateFogSensor(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/fog/generate/")
    }
  
    // @LINE:134
    def publishExpression(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/fog/publish/")
    }
  
    // @LINE:129
    def testFogSensor(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/fog/test/")
    }
  
    // @LINE:135
    def subscribeExpression(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/fog/subscribe/")
    }
  
  }

  // @LINE:6
  class ReverseSwanController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:102
    def testUnregisterTwitterValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/twitter/value")
    }
  
    // @LINE:57
    def testUnregisterCurrencyTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregistercurrencytristate")
    }
  
    // @LINE:119
    def registerExpression(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "register/sound/expression")
    }
  
    // @LINE:140
    def swanPhoneTestUnregister(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/test/unregister/")
    }
  
    // @LINE:71
    def registerExpressionForEmailNotification(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "registeremail")
    }
  
    // @LINE:121
    def registerFogExpression(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/fog/expression")
    }
  
    // @LINE:39
    def testSVEFlink(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registerSVE")
    }
  
    // @LINE:148
    def testUnregisterWebSocketValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/websocket/value")
    }
  
    // @LINE:36
    def testUnregisterTestTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregistertesttristate")
    }
  
    // @LINE:54
    def testRegisterCurrencyTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registercurrencytristate")
    }
  
    // @LINE:98
    def testUnregisterThingSpeakTestValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/thingspeaktest/value")
    }
  
    // @LINE:66
    def testRegisterAll(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registerall")
    }
  
    // @LINE:108
    def testRegisterThingSpeakTestTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/thingspeaktest/tristate")
    }
  
    // @LINE:11
    def swanSongFormService(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swansong")
    }
  
    // @LINE:51
    def testUnregisterCurrencyValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregistercurrencyvalue")
    }
  
    // @LINE:96
    def testRegisterThingSpeakTestValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/thingspeaktest/value")
    }
  
    // @LINE:123
    def registerSensor(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/fog/sensor")
    }
  
    // @LINE:30
    def testUnregisterTestValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/test/value")
    }
  
    // @LINE:76
    def setupWebHook(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "webhook/")
    }
  
    // @LINE:104
    def testRegisterTwitterTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/twitter/tristate")
    }
  
    // @LINE:90
    def testUnregisterLoraValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/lora/value")
    }
  
    // @LINE:92
    def testRegisterThingSpeakValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/thingspeak/value")
    }
  
    // @LINE:139
    def swanPhoneTestRegister(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/test/register/")
    }
  
    // @LINE:112
    def testRegisterGuardianValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/guardian/value")
    }
  
    // @LINE:114
    def testUnregisterGuardianValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/guardian/value")
    }
  
    // @LINE:88
    def testRegisterLoraValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/lora/value")
    }
  
    // @LINE:94
    def testUnregisterThingSpeakValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/thingspeak/value")
    }
  
    // @LINE:45
    def testSound_local(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "testSoundLocal")
    }
  
    // @LINE:33
    def testRegisterTestTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registertesttristate")
    }
  
    // @LINE:84
    def swanPhoneUnregister(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/unregister/")
    }
  
    // @LINE:82
    def swanPhoneRegister(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/register/")
    }
  
    // @LINE:137
    def sendDataToPhone(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swan/test/send/")
    }
  
    // @LINE:73
    def unRegisterExpressionForEmailNotification(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "unregisteremail")
    }
  
    // @LINE:100
    def testRegisterTwitterValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/twitter/value")
    }
  
    // @LINE:117
    def testGetAllSensors(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "sensors")
    }
  
    // @LINE:19
    def testUnregisterRainValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregisterrainvalue")
    }
  
    // @LINE:22
    def testRegisterRainTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registerraintristate")
    }
  
    // @LINE:28
    def testRegisterTestValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/test/value")
    }
  
    // @LINE:110
    def testUnregisterThingSpeakTestTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/thingspeaktest/tristate")
    }
  
    // @LINE:15
    def testWebService(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "push")
    }
  
    // @LINE:60
    def stop(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "stop")
    }
  
    // @LINE:146
    def testRegisterWebSocketValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register/websocket/value")
    }
  
    // @LINE:17
    def testRegisterRainValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registerrainvalue")
    }
  
    // @LINE:13
    def swanSongJSONService(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "swansongjson")
    }
  
    // @LINE:79
    def requestWebHookSwanBot(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "webhook/")
    }
  
    // @LINE:6
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
    // @LINE:49
    def testRegisterCurrencyValueSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "registercurrencyvalue")
    }
  
    // @LINE:68
    def testUnregisterAll(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregisterall")
    }
  
    // @LINE:25
    def testUnregisterRainTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregisterraintristate")
    }
  
    // @LINE:42
    def testSound(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "testSound")
    }
  
    // @LINE:106
    def testUnregisterTwitterTriStateSwan(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unregister/twitter/tristate")
    }
  
  }

  // @LINE:143
  class ReverseWebSocketController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:153
    def computationTest_MEDIAN(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEDIAN/")
    }
  
    // @LINE:154
    def computationTest_MEAN_ON2(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEAN_ON2/")
    }
  
    // @LINE:158
    def computationTest_MEAN_ON3_PARALLEL(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEAN_ON3_PARALLEL/")
    }
  
    // @LINE:155
    def computationTest_MEAN_ON3(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEAN_ON3/")
    }
  
    // @LINE:143
    def socket(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "ws/")
    }
  
    // @LINE:152
    def computationTest_MEAN(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEAN/")
    }
  
    // @LINE:156
    def computationTest_MEAN_PARALLEL(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEAN_PARALLEL/")
    }
  
    // @LINE:157
    def computationTest_MEAN_ON2_PARALLEL(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "kea/test/MEAN_ON2_PARALLEL/")
    }
  
  }


}
