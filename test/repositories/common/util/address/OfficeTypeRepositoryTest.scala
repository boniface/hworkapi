package repositories.common.util.address

/**
  * Created by Aphiwe on 2016/12/12.
  */

import conf.connection.DataConnection
import domain.common.address.OfficeType
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.address. OfficeTypeRepository


import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class OfficeTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    OfficeTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val officeType = OfficeType(
      "OfficeTypeID",
      "boardRoom",
      "321",
      "khayelitsha")

    val result = Await.result(OfficeTypeRepository.save(officeType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetOfficeType") {
    val result = Await.result(OfficeTypeRepository.getOfficeType("OfficeID"), 2.minutes)
    assert( result.head.name === "boardRoom")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    OfficeTypeRepository.truncate().future()
  }

}
