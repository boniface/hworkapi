package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.{MaritalStatus, Race}
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class RaceControllerTest extends PlaySpec with OneAppPerTest{
  val race = Race(
    "A",
    "African")

  "Routes" should {



    "RaceController" should {

      "Create Race Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/race/create")
          .withJsonBody(Json.toJson(race)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Race From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/race/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
