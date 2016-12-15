package app.controllers.payroll.common

import app.services.payroll.common.LeaveTypeService
import domain.payroll.common.LeaveType

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class LeaveTypeController  extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[LeaveType](input).get
      val response =
        for
        {
          results <- LeaveTypeService.apply.createOrUpdate(entity)
        } yield results
        response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getResultById(organisationId: String, leaveTypeId: String) = Action.async {
    request =>
      val response = for {
        results <- LeaveTypeService.apply.getFileResultById(organisationId,leaveTypeId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getLeaveTypes(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- LeaveTypeService.apply.getLeaveType(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}