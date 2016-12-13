package controllers.common.util.education

import domain.common.education.DegreeType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.education.DegreeTypeService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class DegreeTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request=> val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[DegreeType](input).get
      val response = for {
        result <-DegreeTypeService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getDegreeType(degreeTypeId:String) = Action.async{
    request => val response = for {
      result <- DegreeTypeService.apply.getDegreeTypeById(degreeTypeId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception => InternalServerError
      }
  }
}
