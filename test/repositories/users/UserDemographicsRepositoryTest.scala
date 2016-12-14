package repositories.users

import conf.connection.DataConnection
import domain.common.util.Mail
import domain.users.UserDemographics
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.util.MailRepository
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Await

/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserDemographicsRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    UserDemographicsRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val mail = UserDemographics("123",
      "122kuy",
      "demo23",
      "M",
      new DateTime(1985,2,3),
      "S",
      2,
      new DateTime(2016,2,3),
      "Active"
      )

    val result = Await.result(UserDemographicsRepository.save(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMail") {
    val result = Await.result(UserDemographicsRepository.getPersonDemographics("122kuy"), 2.minutes)
    assert( result.head.personDemographicsId === "demo23")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    UserDemographicsRepository.truncate().future()
  }
}
