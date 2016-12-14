package domain.payroll.common

import play.api.libs.json.Json
/**
 * Created by hashcode on 2016/01/09.
 */
case class PensionFund(organisationId:String, pensionFundId:String, name:String)
object PensionFund{
  //start of Louise Editing
  implicit val funderFmt = Json.format[PensionFund]
  //end of Louise Editing

}
