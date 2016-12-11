package services.common.util.Payroll.funding

import domain.payroll.funding.FundingSources
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.payroll.funding.FunderService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class FundingSourcesServiceTest extends FunSuite {

 val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val funder = FundingSources("ORG100", "FS100", "Funder Name", "12345", new DateTime, details)
    val result = Await.result(FunderService.apply.createOrUpdate(funder), 2.minutes)
    assert(result.isExhausted)
  }

  test("testFundingSource") {
    val result = Await.result(FunderService.apply.getFunder("ORG100"), 2.minutes)
    assert(result.head.fundingSourcesId === "FS100")
  }
}