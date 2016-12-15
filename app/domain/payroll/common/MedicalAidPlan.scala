package domain.payroll.common

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/09/25.
  */
case class MedicalAidPlan(medicalAidId:String, name:String, details:Map[String,String])

object MedicalAidPlan{
  //start of Louise Editing
  implicit val funderFmt = Json.format[MedicalAidPlan]
  //end of Louise Editing
}
