package controllers.organisations                                                                                                                                                                                                                                                           //Xolela Masebeni(213160447) xmasebeni1@gmail.com                                                                                                                  //Developed by Masebeni Xolela(213160447), xmasebeni1@gmail.com

import domain.organisations.OrganisationOffice
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationOfficeControllerTest extends PlaySpec with OneAppPerTest{

  val organisationOffice = OrganisationOffice("123","orgOffice101","orgName", "organisation", "active", "office101", "on", new DateTime())

  "Routes" should{

    "OrganisationOfficeController" should{

      "Create OrganisationOffice Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisationOffice/create")
            .withJsonBody(Json.toJson(organisationOffice)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))
      }

      "Get OrganisationOffice From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationOffice/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
