package test.controllers.common.util.payroll.funding

import domain.payroll.funding.FundingSources

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class FundingSourcesControllerTest extends PlaySpec with OneAppPerTest{

  val details: Map[String, String] = Map()
  val fundingSources = FundingSources(
    "ORG100",
    "FS100",
    "FundingSource",
    "12345",
    new DateTime(2016, 11, 9, 12, 0, 0, 0),
    details)

  "Routes" should {
    "FundingSourceController" should {

      "Create FundingSource Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/FundingSource/create")
          .withJsonBody(Json.toJson(fundingSources)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get FundingSource From Controller" in {
        val request = route(app, FakeRequest(GET, "/FundingSource/get/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All FundingSource From Controller" in {
        val request = route(app, FakeRequest(GET, "/FundingSource/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete FundingSource From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/FundingSource/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}


