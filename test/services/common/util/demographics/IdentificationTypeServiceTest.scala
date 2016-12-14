package services.common.util.demographics

import domain.common.demographics.IdentificationType
import org.scalatest.FunSuite
import services.common.demographics.IdentificationTypeService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class IdentificationTypeServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val identificationType = IdentificationType(
      "IdentificationID",
      "work","Permit")

    val result = Await.result(IdentificationTypeService.apply.createOrUpdate(identificationType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetIdentificationType") {
    val result = Await.result(IdentificationTypeService.apply.getIdentificationTypeById("IdentificationTypeID"), 2.minutes)
    assert( result.head.name === "work")
  }
}
