package controllers.storage

import domain.storage.FileResults
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.storage.FileResultsService

/**
  * Created by LuxoloM on 2016/12/13.
  */
class FileResultsController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[FileResults](input).get
      val response = for {
        results <- FileResultsService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getFileResults(organisationId: String, fileResultsId: String) = Action.async {
    request =>
      val response = for {
        results <- FileResultsService.apply.getFileResultsById(organisationId,fileResultsId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
