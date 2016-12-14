package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.LanguageProficiency
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class LanguageProficiencyControllerTest extends PlaySpec with OneAppPerTest{

  val languageProcifiency = LanguageProficiency(
    "1",
    "Excellent")

  "Routes" should {



    "LanguageController" should {

      "Create LanguageProficiency Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/languageProficiency/create")
          .withJsonBody(Json.toJson(languageProcifiency)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get LanguageProficiency From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/languageProficiency/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}
