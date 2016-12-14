package controllers.organisations                                                                                                                                                                                                     //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationContact
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationContactControllerTest extends PlaySpec with OneAppPerTest{

  val organisationContact = OrganisationContact("123","email","078org","tell", Map())

  "Routes" should{

    "OrganisationContactController" should{

      "Create OrganisationContact Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisationContact/create")
            .withJsonBody(Json.toJson(organisationContact)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get OrganisationContact From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationContact/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
