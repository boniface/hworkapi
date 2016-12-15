package services.users

import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by SONY on 2016-12-15.
  */
class UserRoleServiceTest extends FunSuite {

  val details: Map[String, String] = Map()
  test("testSaveOrUpdate") {
    val userRole = UserRole("ORG100",
      "FS100",
      "P10",
      "RSA",
      new DateTime)
    val result = Await.result(UserRoleService.apply.createOrUpdate(userRole), 2.minutes)
    assert(result.isExhausted)
  }

  test("testUserRole") {
    val result = Await.result(UserRoleService.apply.getOrganisationRoles("ORG100"), 2.minutes)
    assert(result.head.userId === "FS100")
  }
}