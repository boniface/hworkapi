package app.controllers.payroll.common

import domain.payroll.common.MedicalAidPlan
import services.payroll.common.MedicalAidPlanService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class MedicalAidPlanController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[MedicalAidPlan](input).get
      val response =
        for
        {
          results <- MedicalAidPlanService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getMAPlanById(medicalAidId: String) = Action.async {
    request =>
      val response = for {
        results <- MedicalAidPlanService.apply.getMedicalAidPlanById(medicalAidId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getMedicalAidPlans(medicalAidId: String) = Action.async {
    request =>
      val response = for {
        results <- MedicalAidPlanService.apply.getMedicalAidPlans(medicalAidId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
