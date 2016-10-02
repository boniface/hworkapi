package factories.payroll.common
import domain.payroll.common.LeaveType

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object LeaveTypeFactory {
  def createLeaveType(values: Map[String, String]): LeaveType = {
    LeaveType(organisationId = values("organisationId"), leaveTypeId = values("leaveTypeId"), name = values("name"))
  }
}
