package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.MaritalStatus
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.MaritalStatusRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class MaritalStatusRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    MaritalStatusRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val maritalStatus = MaritalStatus(
      "MaritalStatusID",
      "Single")

    val result = Await.result(MaritalStatusRepository.save(maritalStatus), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMaritalStatus") {
    val result = Await.result(MaritalStatusRepository.getMaritalStatusById("maritalStatusID"), 2.minutes)
    assert( result.head.name === "Single")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    MaritalStatusRepository.truncate().future()
  }
}
