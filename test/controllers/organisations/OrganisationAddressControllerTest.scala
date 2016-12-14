package controllers.organisations                                                                                                                                                                                                                 //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationAddress
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationAddressControllerTest extends PlaySpec with OneAppPerTest{

  val organisationAddress = OrganisationAddress("123","email","cape101","cape town","cape town101", Map())

  "Routes" should{

    "OrganisationAddressController" should{

      "Create OrganisationAddress Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/address/organisationAddress/create")
            .withJsonBody(Json.toJson(organisationAddress)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get OrganisationAddress From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationAddress/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
