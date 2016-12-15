package controllers.common.util.demographics

import domain.common.demographics.LanguageProficiency
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.LanguageProficiencyService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class LanguageProficiencyController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
    println("THE INPUT IS ", input)
    val entity = Json.fromJson[LanguageProficiency](input).get
    val response = for {
      result <- LanguageProficiencyService.apply.createOrUpdate(entity)
    }yield result
    response.map(ans=>Ok(Json.toJson(entity))).recover{
      case e:Exception=>InternalServerError
    }
 }

  def getLanguageProficiency(languageProficiencyId:String) = Action.async{
    request => val response = for {
      result <- LanguageProficiencyService.apply.getLanguageProficiencyById(languageProficiencyId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{ case e:Exception=>InternalServerError}
  }

}
