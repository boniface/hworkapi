package services.common.util.Payroll.common

import domain.payroll.common.PensionFund
import org.scalatest.FunSuite
import services.payroll.common.PensionFundService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val pensionFund = PensionFund("ORG100", "PF100", "PensionFund Name")
    val result = Await.result(PensionFundService.apply.createOrUpdate(pensionFund), 2.minutes)
    assert(result.isExhausted)
  }

  test("testPensionFund") {
    val result = Await.result(PensionFundService.apply.getPensionFund("ORG100"), 2.minutes)
    assert(result.head.pensionFundId === "PF100")
  }
}