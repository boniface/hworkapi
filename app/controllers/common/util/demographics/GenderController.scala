package controllers.common.util.demographics

import domain.common.demographics.Gender
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class GenderController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[Gender](input).get
      val response = for {
        result<- GenderService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getGender(genderId:String)=Action.async{
    request => val response = for{
      result <- GenderService.apply.getGenderById(genderId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }


}
