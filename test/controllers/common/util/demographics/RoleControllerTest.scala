package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.Role
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class RoleControllerTest extends PlaySpec with OneAppPerTest{

  val role = Role(
    "HR",
    "HumanResource","Hiring")

  "Routes" should {



    "RoleController" should {

      "Create Role Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/role/create")
          .withJsonBody(Json.toJson(role)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Role From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/role/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
