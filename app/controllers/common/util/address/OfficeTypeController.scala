package controllers.common.util.address

import domain.common.address.OfficeType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.address.OfficeTypeService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class OfficeTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val  entity = Json.fromJson[OfficeType](input).get
      val response = for {
        result <- OfficeTypeService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans =>Ok(Json.toJson(entity))).recover{
        case e: Exception => InternalServerError
      }
  }

  def getOfficeType(officeTypeId:String)=Action.async{
    request => val response = for{
      result <- OfficeTypeService.apply.getOfficeTypeById(officeTypeId)
    }yield result
      response.map(ans =>Ok(Json.toJson(ans))).recover{
        case e: Exception =>InternalServerError
      }
  }



}
