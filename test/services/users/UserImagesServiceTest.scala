package services.users

import domain.users.UserImages
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserImagesServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val userImages = UserImages("ORG100",
      "FS100",
      "P10",
      "https://ui.com",
      "useri",
      "mime",
      Option("250"),
      new DateTime)
    val result = Await.result(UserImagesService.apply.createOrupdate(userImages), 2.minutes)
    assert(result.isExhausted)
  }

  test("testUserImages") {
    val result = Await.result(UserImagesService.apply.getAllPersonImages("ORG100"), 2.minutes)
    assert(result.head.userId === "FS100")
  }
}