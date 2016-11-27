package controllers.common.util

import domain.common.util.Mail
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.util.MailService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2015/11/28.
  */
class MailController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[Mail](input).get
      val response = for {
        results <- MailService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getMail(mailId: String) = Action.async {
    request =>
      val response = for {
        results <- MailService.apply.getMail(mailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
