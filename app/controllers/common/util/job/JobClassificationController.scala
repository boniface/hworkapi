package controllers.common.util.job

import domain.common.job.JobClassification
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.job.JobClassificationService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class JobClassificationController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[JobClassification](input).get
      val response = for {
        result <- JobClassificationService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getJobClassification(jobClassificationId:String) = Action.async{
    request => val response = for {
      result <- JobClassificationService.apply.getJobClassificationById(jobClassificationId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
