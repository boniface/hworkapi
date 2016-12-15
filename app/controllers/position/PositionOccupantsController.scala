package controllers.position

import domain.position.PositionOccupants
import services.position.PositionOccupantsService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by Theo on 2016/12/14.
  */
class PositionOccupantsController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PositionOccupants](input).get
      val response =
        for
        {
          results <- PositionOccupantsService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPositionOccupantsById(positionId: String, PositionOccupantsId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionOccupantsService.apply.getPositionOccupantById(positionId, PositionOccupantsId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getPositionOccupants(positionId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionOccupantsService.apply.getPositionOccupants(positionId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
