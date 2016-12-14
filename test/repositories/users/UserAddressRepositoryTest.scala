package repositories.users

import conf.connection.DataConnection
import domain.common.util.Mail
import domain.users.UserAddress
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.util.MailRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserAddressRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    UserAddressRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val mail = UserAddress(
      "tree222",
      "54try",
      "testAddress",
      "Hey22",
      Map("test" -> "587"),
      new DateTime(),
      "Active"
    )

    val result = Await.result(UserAddressRepository.save(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMail") {
    val result = Await.result(UserAddressRepository.getUserAddress("tree222"), 2.minutes)
    assert( result.head.userId === "54try")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    UserAddressRepository.truncate().future()
  }
}
