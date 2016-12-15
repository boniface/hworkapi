package controllers.common.util.demographics

import domain.common.demographics.MaritalStatus
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.MaritalStatusService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class MaritalStatusController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[MaritalStatus](input).get
      val response = for{
        result<-MaritalStatusService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getMaritalStatus(maritalStatusId:String) = Action.async{
    request => val response = for{
      result<-MaritalStatusService.apply.getMaritalStatusById(maritalStatusId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
