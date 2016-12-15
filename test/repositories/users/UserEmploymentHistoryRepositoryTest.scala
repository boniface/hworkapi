package repositories.users

import conf.connection.DataConnection
import domain.users.UserEmploymentHistory
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserEmploymentHistoryRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override def beforeEach() {
    UserEmploymentHistoryRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val user = UserEmploymentHistory(
      "123",
      "fre234",
      "His210",
      "YellowHood",
      "21 Long street",
      "0215984455",
      "John Gilomour",
      "0835468899",
    "Stole from the company",
      new DateTime(2012,1,16),
      new DateTime(2013,6,28),
      2000,
      60000,
      "Rand",
      "Supervisor",
      "111",
      new DateTime(2016,12,16),
      "Active"
    )


    val result = Await.result(UserEmploymentHistoryRepository.save(user), 2.minutes)
    assert(result.isExhausted)
  }
  test("getUserEmploymentHistory"){
    val userHistory = Await.result(UserEmploymentHistoryRepository.getPersonEmploymentHistory("123"),2.minutes)
    assert(userHistory.head.userId === "free234")
  }

  override def afterEach() {
    UserEmploymentHistoryRepository.truncate().future()
  }
}
