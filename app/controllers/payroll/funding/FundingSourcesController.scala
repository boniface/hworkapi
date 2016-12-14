package app.controllers.payroll.funding

import domain.payroll.funding.FundingSources
import services.payroll.funding.FunderService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class FundingSourcesController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FundingSources](input).get
      val response =
        for
        {
          results <- FunderService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getFunderById(organisationId: String, fundingSourcesId: String) = Action.async {
    request =>
      val response = for {
        results <- FunderService.apply.getFileResultById(organisationId, fundingSourcesId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getFunder(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- FunderService.apply.getFunder(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

