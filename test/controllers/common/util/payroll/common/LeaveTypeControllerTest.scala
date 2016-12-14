package test.controllers.common.util.payroll.common

import domain.payroll.common.LeaveType

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class LeaveTypeControllerTest extends PlaySpec with OneAppPerTest{


  val leaveType = LeaveType( "ORG100", "LT100", "Leave Type Name")

  "Routes" should {
    "LeaveTypeController" should {

      "Create LeaveType Object in Controller" in {
        val request = route(app, FakeRequest(POST, "/LeaveType/create")
          .withJsonBody(Json.toJson(leaveType)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get LeaveType From Controller" in {
        val request = route(app, FakeRequest(GET, "/LeaveType/get/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output ", contentAsJson(request))
      }

      "Get All LeaveType From Controller" in {
        val request = route(app, FakeRequest(GET, "/LeaveType/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output is:  ", contentAsJson(request))
      }

      "Delete LeaveType From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/LeaveType/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output: ", contentAsJson(request))
      }
    }
  }
}

