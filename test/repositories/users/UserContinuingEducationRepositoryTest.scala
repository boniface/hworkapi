package repositories.users

import conf.connection.DataConnection
import domain.common.util.Mail
import domain.users.UserContinuingEducation
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.util.MailRepository

import scala.concurrent.Await]
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserContinuingEducationRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    UserContinuingEducationRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val mail = UserContinuingEducation(
      "123",
      "123red",
      "pce123",
      "11CC",
      "ev22",
      "4575ee",
      new DateTime(),
    "Active")

    val result = Await.result(UserContinuingEducationRepository.save(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetUserContinuing") {
    val result = Await.result(UserContinuingEducationRepository.getPersonContinuingEducation("123"), 2.minutes)
    assert( result.head.courseId === "pce123")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    UserContinuingEducationRepository.truncate().future()
  }
}
