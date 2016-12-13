package controllers.training

import domain.common.util.Mail
import domain.training.competencies.Evaluation
import play.api.libs.json.Json
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, Controller}
import services.Training.EvaluationService
import services.common.util.MailService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-02.
 */
class EvaluationController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[Evaluation](input).get
      val response = for {
        results <- EvaluationService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
        case e: Exception => InternalServerError
      }
  }

  def getEvaluationById( id: String) = Action.async {
    request =>
      val response = for {
        results <- EvaluationService.apply.getEvaluationById( id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
