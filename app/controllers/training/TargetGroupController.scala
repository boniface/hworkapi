package controllers.training

import domain.common.util.Mail
import domain.training.courses.TargetGroup
import play.api.libs.json.Json
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, Controller}
import services.Training.TargetGroupService
import services.common.util.MailService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-02.
 */
class TargetGroupController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[TargetGroup](input).get
      val response = for {
        results <- TargetGroupService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
        case e: Exception => InternalServerError
      }
  }

  def getTargetGroupById( id: String) = Action.async {
    request =>
      val response = for {
        results <- TargetGroupService.apply.getTargetGroupById( id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
