package domain.payroll.common

import play.api.libs.json.Json
/**
 * Created by hashcode on 2016/01/09.
 */
case class LeaveType(organisationId:String, leaveTypeId:String, name:String)

object LeaveType{
  //start of Louise Editing
  implicit val funderFmt = Json.format[LeaveType]
  //end of Louise Editing
}
