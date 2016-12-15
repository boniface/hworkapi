package app.controllers.payroll.common

import app.services.payroll.common.BenefitService
import domain.payroll.common.Benefit
import play.mvc.BodyParser.Json
import play.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Malu.Mukendi on 2016-12-09.
  */
class BenefitController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Benefit](input).get
      val response =
        for
        {
          results <- BenefitService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getBenefitById(organisationId: String, benefitId: String) = Action.async {
    request =>
      val response = for {
        results <- BenefitService.apply.getFileResultById(organisationId, benefitId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
