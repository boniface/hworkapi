package domain.payroll.common

import play.api.libs.json.Json
/**
 * Created by hashcode on 2016/01/09.
 */
case class MedicalAid(organisationId:String,medicalAidId:String, name:String, code:String)

object MedicalAid{
  //start of Louise Editing
  implicit val funderFmt = Json.format[MedicalAid]
  //end of Louise Editing

}
