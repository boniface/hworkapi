package services.common.util

import java.util.Date

import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/10/04.
  */
class MailServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val mail = Mail(
      "SITEID",
      "no_reply_no@hashcode.zm",
      "test",
      "smtp.gmail.com",
      "587",
      "ACTIVE",
      new DateTime())

    val result = Await.result(MailService.apply.createOrUpdate(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMail") {
    val result = Await.result(MailService.apply.getMail("SITEID"), 2.minutes)
    assert( result.head.host === "smtp.gmail.com")
  }

}
