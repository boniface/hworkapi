package repositories.users

import conf.connection.DataConnection
import domain.users.UserPosition
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserPositionRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    UserImagesRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val userPosition = UserPosition("ORG100",
      "FS100",
      "P10",
      new DateTime,
      "L2",
      "k1",
      "yes")

    val result = Await.result(UserPositionRepository.save(userPosition), 2.minutes)
    assert(result.isExhausted)
  }

  test("getUserPosition"){
    val result = Await.result(UserPositionRepository.getPersonPosition("ORG123"), 2.minutes)
    assert(result.head.organisationId == "SOU123")
  }

  override protected def afterEach(): Unit = {
    UserPositionRepository.truncate().future()
  }
}