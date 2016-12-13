package services.common.util.Payroll.common

import domain.payroll.common.PensionFundPlan
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.payroll.common.PensionFundPlanService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundPlanServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val pensionFundPlan = PensionFundPlan("PF100", new DateTime(2016, 11, 9, 12, 0, 0, 0), details)
    val result = Await.result(PensionFundPlanService.apply.createOrUpdate(pensionFundPlan), 2.minutes)
    assert(result.isExhausted)
  }

  test("testPensionFundPlan") {
    val result = Await.result(PensionFundPlanService.apply.getPensionFundPlans("PF100"), 2.minutes)
    assert(result.head.date === new DateTime(2016, 11, 9, 12, 0, 0, 0))
  }
}