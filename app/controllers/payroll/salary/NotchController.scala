package app.controllers.payroll.salary

import app.services.payroll.salary.NotchService
import domain.payroll.salary.Notch

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class NotchController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Notch](input).get
      val response =
        for
        {
          results <- NotchService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getNotchById(gradeId: String, id: String) = Action.async {
    request =>
      val response = for {
        results <- NotchService.apply.getNotchById(gradeId, id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getNotches(gradeId: String) = Action.async {
    request =>
      val response = for {
        results <- NotchService.apply.getGradeNotches(gradeId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}


