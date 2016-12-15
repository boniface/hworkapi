package repositories.users

import conf.connection.DataConnection
import domain.users.UserLanguage
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserLanguageRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    UserImagesRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val userLanguage = UserLanguage(
      "ORG100",
      "FS100",
      "P10",
      "L2",
      "yes",
      "yes",
      "yes",
      new DateTime,
      "RSA")

    val result = Await.result(UserLanguageRepository.save(userLanguage), 2.minutes)
    assert(result.isExhausted)
  }

  test("getUserLanguage"){
    val result = Await.result(UserLanguageRepository.getPersonLanguage("ORG123"), 2.minutes)
    assert(result.head.organisationId == "SOU123")
  }

  override protected def afterEach(): Unit = {
    UserImagesRepository.truncate().future()
  }
}

