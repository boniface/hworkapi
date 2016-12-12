package controllers.common.util.address

import com.google.gson.Gson
import domain.common.address.OfficeType
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Aphiwe on 2016/12/12.
  */
class OfficeTypeControllerTest extends PlaySpec with OneAppPerTest{

  val officeType = OfficeType("784","boardRoom","7784","Khayelitsha")

  "Routes" should{
    "OfficeTypeController" should{

      "Create OfficeType Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/address/officeType/create")
          .withJsonBody(Json.toJson(officeType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get OfficeType From Controller" in {
        val request = route(app,FakeRequest(GET,"/address/officeType/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }

}
