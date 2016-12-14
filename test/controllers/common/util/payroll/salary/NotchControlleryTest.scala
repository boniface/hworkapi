package test.controllers.common.util.payroll.salary

import domain.payroll.salary.Notch

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class NotchControllerTest extends PlaySpec with OneAppPerTest{

  val notch = Notch(
    "G100",
    "N100",
    "Grade Name",
    999.12345)

  "Routes" should {
    "NotchController" should {

      "Create Notch Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/Notch/create")
          .withJsonBody(Json.toJson(notch)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Notch From Controller" in {
        val request = route(app, FakeRequest(GET, "/Notch/get/gradeId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is ", contentAsJson(request))
      }

      "Get All Notch From Controller" in {
        val request = route(app, FakeRequest(GET, "/Notch/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete Notch From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/Notch/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}


