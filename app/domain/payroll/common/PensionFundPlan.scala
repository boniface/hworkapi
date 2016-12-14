package domain.payroll.common

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/09/25.
  */
case class PensionFundPlan(pensionFundId:String,date:DateTime,details:Map[String, String] )
object PensionFundPlan{
  //start of Louise Editing
  implicit val funderFmt = Json.format[PensionFundPlan]
  //end of Louise Editing

}
