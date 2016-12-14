package services.common.util.address

import domain.common.address.OfficeType
import org.scalatest.FunSuite
import services.common.address.OfficeTypeService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class OfficeTypeServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val officeType = OfficeType(
      "LocationTypeID",
      "boardRoom","7784","Western Cape")

    val result = Await.result(OfficeTypeService.apply.createOrUpdate(officeType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOfficeType") {
    val result = Await.result(OfficeTypeService.apply.getOfficeTypeById("OfficeTypeID"), 2.minutes)
    assert( result.head.name === "boardRoom")
  }
}
