package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.Language
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class LanguageControllerTest extends PlaySpec with OneAppPerTest{


  val language = Language(
    "001",
    "English")

  "Routes" should {



    "LanguageController" should {

      "Create Language Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/language/create")
          .withJsonBody(Json.toJson(language)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Language From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/language/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}
