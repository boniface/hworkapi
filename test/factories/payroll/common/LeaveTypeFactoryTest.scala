package factories.payroll.common
import domain.payroll.common.LeaveType
import org.scalatest.FunSuite

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class LeaveTypeFactoryTest extends FunSuite {
  test("testCreateLeaveType")
  {
    val values = Map("organisationId"->"1000", "leaveTypeId"->"LT2000", "name"->"Annual")
    val leave = LeaveTypeFactory.createLeaveType(values)
    assert(leave == LeaveType(organisationId="1000", leaveTypeId="LT2000", name="Benefit"))
  }

}
