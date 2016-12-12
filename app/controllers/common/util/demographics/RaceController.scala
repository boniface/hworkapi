package controllers.common.util.demographics

import domain.common.demographics.Race
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.RaceService

/**
  * Created by Aphiwe on 2016/12/12.
  */
class RaceController extends Controller{

  def createOrUpdate = Action.async(parse.json){
    request => val input = request.body
      println("THE INPUT IS ", input)
      val entity = Json.fromJson[Race](input).get
      val response = for {
        result <- RaceService.apply.createOrUpdate(entity)
      }yield result
      response.map(ans=>Ok(Json.toJson(entity))).recover{
        case e:Exception=>InternalServerError
      }
  }

  def getRace(raceId:String) = Action.async{
    request => val response = for {
      result <- RaceService.apply.getRaceById(raceId)
    }yield result
      response.map(ans=>Ok(Json.toJson(ans))).recover {
        case e:Exception=>InternalServerError
      }
  }

}
