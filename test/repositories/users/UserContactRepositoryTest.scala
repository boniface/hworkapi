package repositories.users

import conf.connection.DataConnection
import domain.common.util.
import domain.users.UserContact
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.util.Repository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserContactRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    UserContactRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val contact = UserContact(
      "123",
      "kktest",
      "conTest",
      "67tel",
      Map("test"-> "tested"),
      new DateTime(),
    "Active")

    val result = Await.result(UserContactRepository.save(contact), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetContact") {
    val result = Await.result(UserContactRepository.getUserContact("123"), 2.minutes)
    assert( result.head.userId === "kktest")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    UserContactRepository.truncate().future()
  }
}
