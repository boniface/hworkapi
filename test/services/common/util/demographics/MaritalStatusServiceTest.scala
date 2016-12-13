package services.common.util.demographics

import domain.common.demographics.MaritalStatus
import org.scalatest.FunSuite
import services.common.demographics.MaritalStatusService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class MaritalStatusServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val maritalStatus = MaritalStatus(
      "MaritalStatusID",
      "Single")

    val result = Await.result(MaritalStatusService.apply.createOrUpdate(maritalStatus), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMaritalStatus") {
    val result = Await.result(MaritalStatusService.apply.getMaritalStatusById("MaritalStatusID"), 2.minutes)
    assert( result.head.name === "Single")
  }
}
