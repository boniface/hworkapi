package services.common.util.Payroll.common

import app.services.payroll.common.BenefitService
import domain.payroll.common.Benefit
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class BenefitServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val benefit = Benefit("ORG100", "BN100", "Benefit Name", new DateTime(), "State")
    val result = Await.result(BenefitService.apply.createOrUpdate(benefit), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetBenefit") {
    val result = Await.result(BenefitService.apply.getBenefit("ORG100"), 2.minutes)
    assert(result.head.name === "Benefit Name")
  }
}
