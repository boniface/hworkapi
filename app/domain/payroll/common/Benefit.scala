package domain.payroll.common

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
 * Created by hashcode on 2016/01/09.
 */
case class Benefit( organisationId:String, benefitId:String, name:String,date:DateTime, state:String)

object Benefit{

  //start of Louise Editing
  implicit val funderFmt = Json.format[Benefit]
  //end of Louise Editing

}
