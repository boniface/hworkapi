package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.MaritalStatus
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class MaritalStatusControllerTest extends PlaySpec with OneAppPerTest{
  val maritalStatus = MaritalStatus(
    "S",
    "Single")

  "Routes" should {



    "MaritalStatusController" should {

      "Create MaritalStatus Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/maritalStatus/create")
          .withJsonBody(Json.toJson(maritalStatus)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get MaritalStatus From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/maritalStatus/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}
