package controllers.common.util.demographics

import domain.common.demographics.IdentificationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.IdentificationTypeService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class IdentificationTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ",input)
      val entity = Json.fromJson[IdentificationType](input).get
      val response = for {
        result <- IdentificationTypeService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getIdentificationType(identificationTypeId:String)= Action.async{
    request => val response = for{
      result <-IdentificationTypeService.apply.getIdentificationTypeById(identificationTypeId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{case e:Exception=>InternalServerError}
  }
}
