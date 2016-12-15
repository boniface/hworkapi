package controllers.common.util.education

import domain.common.education.Evaluation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.education.EvaluationService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class EvaluationController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[Evaluation](input).get
      val response = for {
        result <-EvaluationService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getEvaluation(evaluationId:String) = Action.async{
    request => val response = for {
      result <-EvaluationService.apply.getEvaluationById(evaluationId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
