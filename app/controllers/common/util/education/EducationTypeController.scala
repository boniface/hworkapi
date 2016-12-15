package controllers.common.util.education

import domain.common.education.EducationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.education.EducationTypeService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class EducationTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[EducationType](input).get
      val response = for {
        result <- EducationTypeService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getEducationType(educationTypeId:String) = Action.async{
    request => val response = for {
      result <- EducationTypeService.apply.getEducationTypeById(educationTypeId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
