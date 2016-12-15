package controllers.common.util.address

import domain.common.address.LocationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.address.LocationTypeService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class LocationTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[LocationType](input).get
      val response = for {
        result <- LocationTypeService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getLocationType(locationTypeId:String) = Action.async{
    request => val response = for {
      result <- LocationTypeService.apply.getLocationTypeById(locationTypeId)
    }yield result
      response.map(ans =>Ok(Json.toJson(ans))).recover{
        case e: Exception =>InternalServerError
      }
  }


}
