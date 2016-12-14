package controllers.organisations                                                                                                                                                                                                                     //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.Organisation
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by Isiphile on 2016/12/13.
  */
class OrganisationControllerTest extends PlaySpec with OneAppPerTest{

  val organisation = Organisation("123","name",Map(),"adminattached",new DateTime, "cape town101")

  "Routes" should{

    "OrganisationController" should{

      "Create Organisation Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/organisation/create")
            .withJsonBody(Json.toJson(organisation)))
            .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get Organisation From Controller" in {
        val request = route(app,FakeRequest(GET,"/organisation/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
