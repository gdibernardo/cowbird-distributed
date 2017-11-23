
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/gdibernardo/Documents/SWAN-paper/cowbird-distributed/play-java-swan-cloud/conf/routes
// @DATE:Mon Nov 20 11:31:40 CET 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
