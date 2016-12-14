package services.common.util.Payroll.common

import app.services.payroll.common.LeaveTypeService
import domain.payroll.common.LeaveType
import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class LeaveTypeServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val leaveType = LeaveType("ORG100", "Leave Type Name","LT100")
    val result = Await.result(LeaveTypeService.apply.createOrUpdate(leaveType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLeaveType") {
    val result = Await.result(LeaveTypeService.apply.getLeaveType("ORG100"), 2.minutes)
    assert(result.head.organisationId === "ORG100")
  }
}