package controllers.common.util.address

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.address.{AddressType, LocationType}
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class LocationTypeControllerTest extends PlaySpec with OneAppPerTest{

  val locationType = LocationType("321","surbub","7784","Khayelitsha")

  "Routes" should{

    "AddressTypeController" should{

      "Create LocationType Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/address/locationtype/create")
          .withJsonBody(Json.toJson(locationType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get LocationType From Controller" in {
        val request = route(app,FakeRequest(GET,"/address/locationType/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
