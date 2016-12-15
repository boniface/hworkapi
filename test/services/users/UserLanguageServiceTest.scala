package services.users

import domain.users.UserLanguage
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserLanguageServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val userLanguage = UserLanguage("ORG100",
      "FS100",
      "P10",
      "L2",
      "yes",
      "yes",
      "yes",
      new DateTime,
      "RSA")
    val result = Await.result(UserLanguageService.apply.createOrupdate(userLanguage), 2.minutes)
    assert(result.isExhausted)
  }

  test("testUserLanguage") {
    val result = Await.result(UserLanguageService.apply.getAllPersonLanguages("ORG100"), 2.minutes)
    assert(result.head.userId === "FS100")
  }
}
