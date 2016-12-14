package repositories.common.util

import java.util.Date

import conf.connection.DataConnection
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/10/04.
  */
class MailRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    MailRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val mail = Mail(
      "SITEID",
      "no_reply_no@hashcode.zm",
      "test",
      "smtp.gmail.com",
      "587",
      "ACTIVE",
      new DateTime())

    val result = Await.result(MailRepository.save(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMail") {
    val result = Await.result(MailRepository.getMail("SITEID"), 2.minutes)
    assert( result.head.host === "smtp.gmail.com")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    MailRepository.truncate().future()
  }
}
