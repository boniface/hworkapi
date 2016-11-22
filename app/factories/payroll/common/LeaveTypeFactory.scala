package factories.payroll.common

import domain.payroll.common.LeaveType

/**
  * Created by SONY on 2016-10-18.
  */
class LeaveTypeFactory
{
  def createLeaveType(values: Map[String, String]): LeaveType=
    LeaveType(organisationId = values("organisationId"), name = values("name"), leaveTypeId = values("leaveTypeId"))

}
