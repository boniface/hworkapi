package controllers.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.address.{AddressType, LocationType}
import domain.common.demographics.Gender
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class GenderControllerTest extends PlaySpec with OneAppPerTest{
  val gender = Gender(
    "M","Male")

  "Routes" should {



    "GenderController" should {

      "Create Gender Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/demographics/gender/create")
          .withJsonBody(Json.toJson(gender)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Gender From Controller" in {
        val request = route(app, FakeRequest(GET, "/demographics/gender/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
