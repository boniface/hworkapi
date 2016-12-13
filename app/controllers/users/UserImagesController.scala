package controllers.users

import domain.users.UserImages
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserImagesService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class UserImagesController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[UserImages](input).get
      val response = for {
        results <- UserImagesService.apply.createOrupdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserImages(organisationId: String, userId: String, personImagesId: String) = Action.async {
    request =>
      val response = for {
        results <- UserImagesService.apply.getPersonImagesById(organisationId,userId,personImagesId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
