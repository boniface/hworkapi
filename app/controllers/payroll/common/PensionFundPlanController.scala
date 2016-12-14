package app.controllers.payroll.common

import domain.payroll.common.PensionFundPlan
import services.payroll.common.PensionFundPlanService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundPlanController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PensionFundPlan](input).get
      val response =
        for
        {
          results <- PensionFundPlanService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPensionPlanById(pensionFundId: String) = Action.async {
    request =>
      val response = for {
        results <- PensionFundPlanService.apply.getPensionFundPlanById(pensionFundId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getPensionFundPlan(pensionFundId: String) = Action.async {
    request =>
      val response = for {
        results <- PensionFundPlanService.apply.getPensionFundPlans(pensionFundId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}


