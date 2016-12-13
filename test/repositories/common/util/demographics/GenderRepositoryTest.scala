package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.Gender
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.GenderRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class GenderRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    GenderRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val gender = Gender(
      "genderID",
      "MALE")

    val result = Await.result(GenderRepository.save(gender), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetGender") {
    val result = Await.result(GenderRepository.getGender("genderID"), 2.minutes)
    assert( result.head.name === "Male")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    GenderRepository.truncate().future()
  }

}
