package controllers.organisations                                                                                                                                                                                                                                                 //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationDocuments
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationDocumentsControllerTest extends PlaySpec with OneAppPerTest{

  val organisationDocuments = OrganisationDocuments("123","orgDoc101", "description","www.org.com", "mime", new DateTime(), Set(), "active")

  "Routes" should{

    "OrganisationDocumentsController" should{

      "Create OrganisationDocuments Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisationDocuments/create")
            .withJsonBody(Json.toJson(organisationDocuments)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))
      }

      "Get OrganisationDocuments From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationDocuments/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
