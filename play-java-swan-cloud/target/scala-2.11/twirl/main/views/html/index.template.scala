
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*11.4*/("""

    """),format.raw/*16.8*/("""
    """),format.raw/*17.51*/("""

    """),format.raw/*19.5*/("""<form action=""""),_display_(/*19.20*/routes/*19.26*/.SwanController.swanSongFormService()),format.raw/*19.63*/("""" method="post">

     <input type="text" name="name" value="self@movement:x"""),format.raw/*21.60*/("""{"""),format.raw/*21.61*/("""ANY,1000"""),format.raw/*21.69*/("""}"""),format.raw/*21.70*/("""" size="35"/>

     <button> Check SWAN Song</button>

    </form>


"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/*
 * This template takes a single argument, a String containing a
 * message to display.
 */
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Thu Nov 09 17:16:07 CET 2017
                  SOURCE: /Users/gdibernardo/Documents/SWAN-paper/cowbird-distributed/play-java-swan-cloud/app/views/index.scala.html
                  HASH: c1594a4cd882e7552e9829af0bd247c14b800f2b
                  MATRIX: 834->95|946->112|975->308|1008->437|1041->488|1074->494|1116->509|1131->515|1189->552|1293->629|1322->630|1358->638|1387->639
                  LINES: 30->5|35->5|37->11|39->16|40->17|42->19|42->19|42->19|42->19|44->21|44->21|44->21|44->21
                  -- GENERATED --
              */
          