package controllers.position

import domain.position.PositionEvent
import services.position.PositionEventService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Theo on 2016/12/14.
  */
class PositionEventController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PositionEvent](input).get
      val response =
        for
        {
          results <- PositionEventService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPositionEventById(positionId: String, PositionEventId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionEventService.apply.getEventById(positionId, PositionEventId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getPositionEvents(positionId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionEventService.apply.getPositionEvents(positionId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
