package controllers.storage

import domain.storage.FileMeta
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.storage.FileMetaService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class FileMetaController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[FileMeta](input).get
      val response = for {
        results <- FileMetaService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getFileMeta(fileMetaId: String) = Action.async {
    request =>
      val response = for {
        results <- FileMetaService.apply.getFileMetaById(fileMetaId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
