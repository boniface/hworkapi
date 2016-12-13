package controllers.users

import domain.users.UserPosition
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserPositionService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class UserPositionController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[UserPosition](input).get
      val response = for {
        results <- UserPositionService.apply.createOrupdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getPersonPosition(organisationId: String, userId: String, personPositionId: String) = Action.async {
    request =>
      val response = for {
        results <- UserPositionService.apply.getPersonPositionById(organisationId,userId,personPositionId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
