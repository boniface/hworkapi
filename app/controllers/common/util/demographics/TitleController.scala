package controllers.common.util.demographics

import domain.common.demographics.Title
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.TitleService



/**
  * Created by Aphiwe on 2016/12/12.
  */
class TitleController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[Title](input).get
      val response = for {
        result <- TitleService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getTitle(titleId:String) = Action.async{
    request => val response = for {
      result <- TitleService.apply.getTitleById(titleId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover{
        case e:Exception=>InternalServerError
      }
  }

}
