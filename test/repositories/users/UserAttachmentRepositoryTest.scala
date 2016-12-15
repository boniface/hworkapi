package repositories.users

import conf.connection.DataConnection
import domain.common.util.Mail
import domain.users.UserAttachment
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.util.MailRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Lonwabo on 12/14/2016.
  */
class UserAttachmentRepositoryTest extends FunSuite with BeforeAndAfterEach {

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    UserAttachmentRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val mail = UserAttachment(
      "123",
      "ku456",
      "tpa55",
      "www.size.co.za",
      "test",
      "testing",
      new DateTime())

    val result = Await.result(UserAttachmentRepository.save(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMail") {
    val result = Await.result(UserAttachmentRepository.getPersonAttachment("123"), 2.minutes)
    assert( result.head.userId === "ku456")
  }


  override protected def afterEach(): Unit = {
    //Delete All records
    UserAttachmentRepository.truncate().future()
  }
}
