package controllers.common.util.demographics

import domain.common.demographics.Role
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.RoleService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class RoleController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[Role](input).get
      val response = for {
        result <- RoleService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getRole(roleId:String) = Action.async{
    request=> val response = for {
      result <- RoleService.apply.getRoleById(roleId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
