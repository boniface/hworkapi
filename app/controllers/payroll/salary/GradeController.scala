package app.controllers.payroll.salary

import app.services.payroll.salary.GradeService
import domain.payroll.salary.Grade

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class GradeController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Grade](input).get
      val response =
        for
        {
          results <- GradeService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPensionById(organisationId: String, gradeId: String) = Action.async {
    request =>
      val response = for {
        results <- GradeService.apply.getGradeById(organisationId, gradeId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getGrades(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- GradeService.apply.getCompanyGrades(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}



