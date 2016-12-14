package repositories.users

import conf.connection.DataConnection
import domain.users.UserIdentity
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Lonwabo on 12/13/2016.
  */
class UserIdentityRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override def beforeEach() {
    UserIdentityRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val userIdentity = UserIdentity(
      "123",
      "de234",
      "9409084675412",
      "Card",
      "009",
      new DateTime(),
      "active"
    )

    val result = Await.result(UserIdentityRepository.save(userIdentity), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetUserIdentity"){
    val result = Await.result(UserIdentityRepository.getPersonIdentity("123"), 2.minutes)
    assert( result.head.userId === "de234")
  }
  override def afterEach() {
    UserIdentityRepository.truncate().future()
  }
}
