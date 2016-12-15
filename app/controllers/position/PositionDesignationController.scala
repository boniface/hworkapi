package controllers.position

import domain.position.PositionDesignation
import services.position.PositionDesignationService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Theo on 2016/12/14.
  */
class PositionDesignationController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PositionDesignation](input).get
      val response =
        for
        {
          results <- PositionDesignationService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getPositionDesignationById(positionId: String, PositionDesignationId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionDesignationService.apply.getDesignationById(positionId, PositionDesignationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getPositionDesignations(positionId: String) = Action.async {
    request =>
      val response = for {
        results <- PositionDesignationService.apply.getPositionDesignations(positionId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}
