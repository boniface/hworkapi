package repositories.common.util.payroll.common

import conf.connection.DataConnection
import domain.payroll.common.LeaveType
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.payroll.common.LeaveTypeRepository
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class LeaveTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    LeaveTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val leaveType = LeaveType(
      "ORG100",
      "LT100",
      "Leave Type Name")

    val result = Await.result(LeaveTypeRepository.save(leaveType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLeaveType") {
    val result = Await.result(LeaveTypeRepository.getLeaveType("ORG100"), 2.minutes)
    assert(result.head.name === "Leave Type Name")
  }

  test("testFindAllLeaveType") {
    val result = Await.result(LeaveTypeRepository.findAll, 2.minutes)
    assert( result.size > 0)
  }
  override protected def afterEach(): Unit = {
    LeaveTypeRepository.truncate().future()
  }
}


