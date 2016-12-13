package services.common.util.demographics

import domain.common.demographics.Role
import org.scalatest.FunSuite
import services.common.demographics.RoleService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class RoleServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val role = Role(
      "RoleID",
      "Developer","Building applications")

    val result = Await.result(RoleService.apply.createOrUpdate(role), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetRole") {
    val result = Await.result(RoleService.apply.getRoleById("RoleID"), 2.minutes)
    assert( result.head.name === "Developer")
  }
}
