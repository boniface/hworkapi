package controllers.training

import domain.common.util.Mail
import domain.training.schedules.ScheduledCourse
import play.api.libs.json.Json
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, Controller}
import services.Training.ScheduledCourseService
import services.common.util.MailService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-02.
 */
class ScheduledCourseController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[ScheduledCourse](input).get
      val response = for {
        results <- ScheduledCourseService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
        case e: Exception => InternalServerError
      }
  }

  def getScheduledCourse( id: String) = Action.async {
    request =>
      val response = for {
        results <- ScheduledCourseService.apply.getScheduledCourse(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
