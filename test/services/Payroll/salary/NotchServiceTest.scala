package services.common.util.Payroll.salary

import app.services.payroll.salary.NotchService
import domain.payroll.salary.Notch
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class NotchServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val notch = Notch("G100", "N100", "Grade Name", 650.12345)
    val result = Await.result(NotchService.apply.createOrUpdate(notch), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetNotch") {
    val result = Await.result(NotchService.apply.getGradeNotches("G100"), 2.minutes)
    assert(result.head.id === "N100")
  }
}
