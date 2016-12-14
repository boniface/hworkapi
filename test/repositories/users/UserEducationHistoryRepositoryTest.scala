package repositories.users

import conf.connection.DataConnection
import domain.users.UserEducationHistory
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserEducationHistoryRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override def beforeEach() {
    UserEducationHistoryRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val user = UserEducationHistory(
      "123",
      "123dee",
      "s200",
      "cape Tech",
      "Cape Town",
      2015,
      "ND",
      "Diploma",
      "Passed cum laude",
      new DateTime(2016,7,9),
      "Active"
    )
    val result = Await.result(UserEducationHistoryRepository.save(user), 2.minutes)
    assert(result.isExhausted)
  }

  test("getUserEducationHistory"){
    val result = Await.result(UserEducationHistoryRepository.getPersonEducationHistory("123"), 2.minutes)
    assert(result.head.userId === "123dee")
  }

  override def afterEach() {
    UserEducationHistoryRepository.truncate().future()
  }
}
