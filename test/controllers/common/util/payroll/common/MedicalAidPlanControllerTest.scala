package test.controllers.common.util.payroll.common

import domain.payroll.common.MedicalAidPlan

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidPlanControllerTest extends PlaySpec with OneAppPerTest{

  val details: Map[String, String] = Map()
  val medicalAidPlan = MedicalAidPlan("MA100",
    "Medical Aid Plan Name",
    details)

  "Routes" should {
    "MedicalAidPlanController" should {

      "Create MedicalAidPlan Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/MedicalAidPlan/create")
          .withJsonBody(Json.toJson(medicalAidPlan)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get MedicalAidPlan From Controller" in {
        val request = route(app, FakeRequest(GET, "/MedicalAidPlan/get/medicalAidId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All MedicalAidPlan From Controller" in {
        val request = route(app, FakeRequest(GET, "/MedicalAidPlan/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete MedicalAidPlan From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/MedicalAidPlan/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}

