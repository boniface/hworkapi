package test.controllers.common.util.payroll.common

import domain.payroll.common.PensionFundPlan

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundPlanControllerTest extends PlaySpec with OneAppPerTest{

  val details: Map[String, String] = Map()
  val pensionFundPlan = PensionFundPlan("PF100",
    new DateTime(2016, 11, 9, 12, 0, 0, 0),
    details)

  "Routes" should {
    "PensionFundPlanController" should {

      "Create PensionFundPlan Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/PensionFundPlan/create")
          .withJsonBody(Json.toJson(pensionFundPlan)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get PensionFundPlan From Controller" in {
        val request = route(app, FakeRequest(GET, "/PensionFundPlan/get/pensionFundId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All PensionFundPlan From Controller" in {
        val request = route(app, FakeRequest(GET, "/PensionFundPlan/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete PensionFundPlan From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/PensionFundPlan/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}


