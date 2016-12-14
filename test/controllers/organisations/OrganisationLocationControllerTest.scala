package controllers.organisations                                                                                                                                                                                                                                                                                   //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationLocation
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationLocationControllerTest extends PlaySpec with OneAppPerTest{

  val organisationLocation = OrganisationLocation("123","orgLoc101","orgName","orgLocType101", "101","latitude","longitude", "parent101", "active", new DateTime())

  "Routes" should{

    "OrganisationLocationController" should{

      "Create OrganisationLocation Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisationLocation/create")
            .withJsonBody(Json.toJson(organisationLocation)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))
      }

      "Get OrganisationLocation From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisationLocation/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
