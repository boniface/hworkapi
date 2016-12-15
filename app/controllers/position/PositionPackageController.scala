package controllers.position

import domain.position.PositionPackage
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.PositionPackageService

/**
  * Created by Yusiry on 12/15/2016.
  */
class PositionPackageController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PositionPackage](input).get
      val response =
        for {
          results <- PositionPackageService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover{
        case e: Exception => InternalServerError
      }
  }

  def getPositionPackage(positionId: String, packageId: String) = Action.async(parse.json){
    request =>
      val response = for{
        results <- PositionPackageService.apply.getPositionPackageById(positionId, packageId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
