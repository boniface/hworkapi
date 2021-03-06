package controllers.job

import domain.job.{ JobEvent}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.job.JobEventService

/**
  * Created by SONY on 2016-12-14.
  */
class JobEventController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[JobEvent](input).get
      val response = for {
        results <- JobEventService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getJobEvent(jobId: String, id: String) = Action.async {
    request =>
      val response = for {
        results <- JobEventService.apply.getJobEventById(jobId, id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}