package controllers.position

import domain.position.PositionFunding
import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import services.position.PositionFundingService

/**
  * Created by Yusiry on 12/15/2016.
  */
class PositionFundingController extends Controller {

  def createOrUpdate = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[PositionFunding](input).get
      val response =
        for {
          results <- PositionFundingService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover{
        case e: Exception => InternalServerError
      }

  }

  def getPositionFunding(positionId: String, organisationId: String) = Action.async{
    request =>
      val response = for {
        results <- PositionFundingService.apply.getPositionFundingById(positionId, organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}
