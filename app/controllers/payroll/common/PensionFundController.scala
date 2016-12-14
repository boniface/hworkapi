package app.controllers.payroll.common

import domain.payroll.common.PensionFund
import services.payroll.common.PensionFundService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class PensionFundController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PensionFund](input).get
      val response =
        for
        {
          results <- PensionFundService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPensionById(organisationId: String, pensionFundId: String) = Action.async {
    request =>
      val response = for {
        results <- PensionFundService.apply.getFileResultById(organisationId, pensionFundId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getPensionFund(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- PensionFundService.apply.getPensionFund(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}


