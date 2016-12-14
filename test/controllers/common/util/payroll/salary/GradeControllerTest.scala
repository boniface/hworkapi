package test.controllers.common.util.payroll.salary

import domain.payroll.salary.Grade

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class GradeControllerTest extends PlaySpec with OneAppPerTest{

  val grade = Grade(
    "ORG100",
    "G100",
    "Grade Name",
    12345,
    0.12345,
    999.12345,
    "US100",
    new DateTime(2016, 11, 9, 12, 0, 0, 0),
    "notes")

  "Routes" should {
    "GradeController" should {

      "Create Grade Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/Grade/create")
          .withJsonBody(Json.toJson(grade)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Grade From Controller" in {
        val request = route(app, FakeRequest(GET, "/Grade/get/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All Grade From Controller" in {
        val request = route(app, FakeRequest(GET, "/Grade/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete Grade From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/Grade/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}


