package controllers.common.util.address

import domain.common.address.AddressType
import com.google.gson.Gson
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by Aphiwe on 2016/12/12.
  */
class AddressTypeControllerTest extends PlaySpec with OneAppPerTest{

  val addressType = AddressType("123","home adress")

  "Routes" should{

    "AddressTypeController" should{

      "Create AddressType Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/address/addresstype/create")
            .withJsonBody(Json.toJson(addressType)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get AddressType From Controller" in {
        val request = route(app,FakeRequest(GET,"/address/addresstype/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
