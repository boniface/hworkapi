package services.common.util.address

import domain.common.address.AddressType
import org.scalatest.FunSuite
import services.common.address.AddressTypeService

/**
  * Created by Aphiwe on 2016/12/13.
  */

import scala.concurrent.Await
import scala.concurrent.duration._

class AddressTypeServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val jobClassification = AddressType(
      "AddressTypeID",
      "Home")

    val result = Await.result(AddressTypeService.apply.createOrUpdate(jobClassification), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetAddressType") {
    val result = Await.result(AddressTypeService.apply.getAddressTypeById("AddressTypeID"), 2.minutes)
    assert( result.head.name === "Home")
  }
}
