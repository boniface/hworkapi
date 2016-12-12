package controllers.common.util.address

import domain.common.address.AddressType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.address.AddressTypeService


/**
  * Created by Aphiwe on 2016/12/12.
  */
class AddressTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request=>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[AddressType](input).get
      val response = for {
        result <-AddressTypeService.apply.createOrUpdate(entity)
      }yield result

      response.map(ans =>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getAddressType(addressTypeId:String) = Action.async{
    request => val response = for {
      result <- AddressTypeService.apply.getAddressTypes(addressTypeId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
