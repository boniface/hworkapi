package controllers.organisations                                                                                                                                                                                                                                       //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationLogo
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationLogoControllerTest extends PlaySpec with OneAppPerTest{

  val organisationLogo = OrganisationLogo("123","org101","org.com", Option("size"), "logo", "mime", new DateTime())

  "Routes" should{

    "OrganisationLogoController" should{

      "Create OrganisationLogo Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisationLogo/create")
            .withJsonBody(Json.toJson(organisationLogo)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get OrganisationLogo From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationLogo/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
