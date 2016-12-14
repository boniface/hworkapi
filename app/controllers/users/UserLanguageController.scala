package controllers.users

import domain.users.UserLanguage
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserLanguageService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class UserLanguageController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[UserLanguage](input).get
      val response = for {
        results <- UserLanguageService.apply.createOrupdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserLanguage(organisationId: String, userId: String, personLanguageId: String) = Action.async {
    request =>
      val response = for {
        results <- UserLanguageService.apply.getPersonLanguageById(organisationId, userId, personLanguageId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}


