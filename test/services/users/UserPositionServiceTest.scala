package services.users

import domain.users.UserPosition
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by SONY on 2016-12-15.
  */
class UserPositionServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val userPosition = UserPosition("ORG100",
      "FS100",
      "P10",
      new DateTime,
      "L2",
      "k1",
      "yes")
    val result = Await.result(UserPositionService.apply.createOrupdate(userPosition), 2.minutes)
    assert(result.isExhausted)
  }

  test("testUserPosition") {
    val result = Await.result(UserPositionService.apply.getAllPersonPositions("ORG100"), 2.minutes)
    assert(result.head.userId === "FS100")
  }
}
