package controllers.training

import domain.common.util.Mail
import domain.training.courses.CourseCompetencies
import play.api.libs.json.Json
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, Controller}
import services.Training.CourseCompetenciesService
import services.common.util.MailService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-02.
 */
class CourseCompetenciesController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[CourseCompetencies](input).get
      val response = for {
        results <- CourseCompetenciesService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
        case e: Exception => InternalServerError
      }
  }

  def getCourseCompetencies( id: String) = Action.async {
    request =>
      val response = for {
        results <- CourseCompetenciesService.apply.getCourseCompetencies(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
