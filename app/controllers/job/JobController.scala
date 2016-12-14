package controllers.job

import domain.job.Job
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.job.JobService
/**
  * Created by SONY on 2016-12-14.
  */
class JobController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[Job](input).get
      val response = for {
        results <- JobService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getCompanyJobs(company: String, id: String) = Action.async {
    request =>
      val response = for {
        results <- JobService.apply.getJobById(company, id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}