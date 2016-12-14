package controllers.common.util.education

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.Title
import domain.common.education.{DegreeType, EducationType}
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class EducationTypeControllerTest extends PlaySpec with OneAppPerTest{
  val educationType = EducationType(
    "D",
    "Diploma")

  "Routes" should {



    "EducationTypeController" should {

      "Create EducationType Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/education/educationType/create")
          .withJsonBody(Json.toJson(EducationType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get EducationType From Controller" in {
        val request = route(app, FakeRequest(GET, "/education/educationType/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }


}
