package controllers.users

import domain.users.UserRole
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserRoleService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class UserRoleController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[UserRole](input).get
      val response = for {
        results <- UserRoleService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserRoles(organisationId: String, userId: String) = Action.async {
    request =>
      val response = for {
        results <- UserRoleService.apply.getUserRoles(organisationId, userId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

