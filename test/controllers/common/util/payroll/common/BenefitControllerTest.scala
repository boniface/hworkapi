package test.controllers.common.util.payroll.common

import domain.payroll.common.Benefit

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class BenefitControllerTest extends PlaySpec with OneAppPerTest{


  val benefit = Benefit("ORG100", "BN100", "Benefit Name", new DateTime(2016, 11, 9, 12, 0, 0, 0), "State")

  "Routes" should {
    "BenefitController" should {

      "Create Benefit Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/Benefit/create")
          .withJsonBody(Json.toJson(benefit)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Benefit From Controller" in {
        val request = route(app, FakeRequest(GET, "/Benefit/get/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All Benefit From Controller" in {
        val request = route(app, FakeRequest(GET, "/Benefit/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete Benefit From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/Benefit/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}

