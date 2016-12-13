package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.{Gender, IdentificationType}
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class IdentificationTypeControllerTest extends PlaySpec with OneAppPerTest{

  val identificationType = IdentificationType(
    "5412","contact","person contact name")

  "Routes" should {



    "IdentificationTypeController" should {

      "Create IdentificationType Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/identificationType/create")
          .withJsonBody(Json.toJson(identificationType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get IdentificationType From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/identificationType/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
