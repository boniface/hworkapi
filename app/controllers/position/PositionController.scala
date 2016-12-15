package controllers.position

import domain.position.Position
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.PositionService

/**
  * Created by Yusiry on 12/15/2016.
  */
class PositionController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Position](input).get
      val response =
        for {
          results <- PositionService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPosition(organisationId: String, positionId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionService.apply.getPositionById(organisationId, positionId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
