package app.controllers.payroll.common

import app.services.payroll.common.MedicalAidService
import domain.payroll.common.MedicalAid

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[MedicalAid](input).get
      val response =
        for
        {
          results <- MedicalAidService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getMedicalAidById(organisationId: String, medicalAidId: String) = Action.async {
    request =>
      val response = for {
        results <- MedicalAidService.apply.getFileResultById(organisationId, medicalAidId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getMedicalAid(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- MedicalAidService.apply.getMedicalAid(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

