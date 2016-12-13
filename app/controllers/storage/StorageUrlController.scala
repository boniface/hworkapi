package controllers.storage

import domain.storage.StorageUrl
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.storage.StorageUrlService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class StorageUrlController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[StorageUrl](input).get
      val response = for {
        results <- StorageUrlService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getStorageUrl(organisationId: String, storageUrlId: String) = Action.async {
    request =>
      val response = for {
        results <- StorageUrlService.apply.getStorageUrlById(organisationId,storageUrlId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

