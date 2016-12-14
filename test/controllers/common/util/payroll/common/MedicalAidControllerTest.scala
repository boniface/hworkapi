package test.controllers.common.util.payroll.common

import domain.payroll.common.MedicalAid

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidControllerTest extends PlaySpec with OneAppPerTest{

  val medicalAid = MedicalAid("ORG100",
    "MA100",
    "Medical Aid Name",
    "12345")

  "Routes" should {
    "MedicalAidController" should {

      "Create MedicalAid Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/MedicalAid/create")
          .withJsonBody(Json.toJson(medicalAid)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get MedicalAid From Controller" in {
        val request = route(app, FakeRequest(GET, "/MedicalAid/get/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All MedicalAid From Controller" in {
        val request = route(app, FakeRequest(GET, "/MedicalAid/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete MedicalAid From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/MedicalAid/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}


