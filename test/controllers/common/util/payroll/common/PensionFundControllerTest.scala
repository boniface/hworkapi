package test.controllers.common.util.payroll.common

import domain.payroll.common.PensionFund

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundControllerTest extends PlaySpec with OneAppPerTest{

  val details: Map[String, String] = Map()
  val pensionFund = PensionFund("ORG100",
    "PF100",
    "Pension Fund Name")

  "Routes" should {
    "PensionFundController" should {

      "Create PensionFund Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/PensionFund/create")
          .withJsonBody(Json.toJson(pensionFund)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get PensionFund From Controller" in {
        val request = route(app, FakeRequest(GET, "/PensionFund/get/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All PensionFund From Controller" in {
        val request = route(app, FakeRequest(GET, "/PensionFund/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete PensionFund From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/PensionFund/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}


