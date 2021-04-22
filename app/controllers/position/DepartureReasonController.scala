package controllers.position

import domain.position.DepartureReason
import services.position.DepartureReasonService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Theo on 14-Dec-16.
  */
class DepartureReasonController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[DepartureReason](input).get
      val response =
        for
        {
          results <- DepartureReasonService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getDepartureReasonById(organisationId: String, DepartureReasonId: String) = Action.async {
    request =>
      val response = for {
        results <- DepartureReasonService.apply.getDepartureReasonById(organisationId, DepartureReasonId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getDepartureReason(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- DepartureReasonService.apply.getDepartureReasons(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}
