package controllers.organisations                                                                                                                                                                                                                                                 //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationDepartment
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationDepartmentControllerTest extends PlaySpec with OneAppPerTest{

  val organisationDepartment = OrganisationDepartment("123","org101","orgName","description", "active","on", new DateTime())

  "Routes" should{

    "OrganisationDepartmentController" should{

      "Create OrganisationDepartment Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisationDepartment/create")
            .withJsonBody(Json.toJson(organisationDepartment)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))
      }

      "Get OrganisationDepartment From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationDepartment/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
