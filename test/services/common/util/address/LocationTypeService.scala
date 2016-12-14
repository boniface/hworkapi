package services.common.util.address

import domain.common.address.LocationType
import org.scalatest.FunSuite
import services.common.address.LocationTypeService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class LocationTypeService extends FunSuite{

  test("testSaveOrUpdate") {
    val locationType = LocationType(
      "LocationTypeID",
      "Surbub","7784","Western Cape")

    val result = Await.result(LocationTypeService.apply.createOrUpdate(locationType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLocationType") {
    val result = Await.result(LocationTypeService.apply.getLocationTypeById("LocationTypeID"), 2.minutes)
    assert( result.head.name === "Surbub")
  }
}
