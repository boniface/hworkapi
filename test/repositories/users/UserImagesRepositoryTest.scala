package repositories.users

import conf.connection.DataConnection
import domain.users.UserImages
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserImagesRepositoryTest  extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    UserImagesRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val userImages = UserImages(
      "ORG100",
      "FS100",
      "P10",
      "https://ui.com",
      "useri",
      "mime",
       Option("250"),
       new DateTime)

    val result = Await.result(UserImagesRepository.save(userImages), 2.minutes)
    assert(result.isExhausted)
  }

  test("getUserImages"){
    val result = Await.result(UserImagesRepository.getPersonImages("ORG123"), 2.minutes)
    assert(result.head.organisationId == "SOU123")
  }

  override protected def afterEach(): Unit = {
    UserImagesRepository.truncate().future()
  }
}
