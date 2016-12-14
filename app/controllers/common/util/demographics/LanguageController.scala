package controllers.common.util.demographics

import domain.common.demographics.Language
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.LanguageService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class LanguageController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[Language](input).get
      val response = for{
        result <- LanguageService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getLanguage(languageId:String)=Action.async{
    request=>val response = for {
      result <- LanguageService.apply.getLanguageById(languageId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{case e:Exception=>InternalServerError}
  }

}
