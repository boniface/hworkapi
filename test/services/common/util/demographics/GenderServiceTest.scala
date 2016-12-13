package services.common.util.demographics

import domain.common.demographics.Gender
import org.scalatest.FunSuite
import services.common.demographics.GenderService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class GenderServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val gender = Gender(
      "GenderID",
      "male")

    val result = Await.result(GenderService.apply.createOrUpdate(gender), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetGender") {
    val result = Await.result(GenderService.apply.getGenderById("OfficeTypeID"), 2.minutes)
    assert( result.head.name === "male")
  }

}
