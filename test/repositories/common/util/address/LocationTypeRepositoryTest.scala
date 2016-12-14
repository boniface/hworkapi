package repositories.common.util.address

/**
  * Created by Aphiwe on 2016/12/12.
  */

import conf.connection.DataConnection
import domain.common.address.LocationType
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.address.LocationTypeRepository
import repositories.common.location.LocationRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
class LocationTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    LocationTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val locationType = LocationType(
      "LocationID",
      "surbub",
      "7784",
      "khayelitsha")

    val result = Await.result(LocationTypeRepository.save(locationType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetLocationType") {
    val result = Await.result(LocationRepository.getLocation("LocationID"), 2.minutes)
    assert( result.head.name === "surbub")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    LocationRepository.truncate().future()
  }
}
