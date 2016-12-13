package controllers.common.util.education

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.Title
import domain.common.education.DegreeType
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class DegreeTypeControllerTest extends PlaySpec with OneAppPerTest{
  val degreeType = DegreeType(
    "LLB",
    "Law")

  "Routes" should {



    "DegreeTypeController" should {

      "Create DegreeType Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/education/degreeType/create")
          .withJsonBody(Json.toJson(DegreeType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get DegreeType From Controller" in {
        val request = route(app, FakeRequest(GET, "/education/degreeType/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
