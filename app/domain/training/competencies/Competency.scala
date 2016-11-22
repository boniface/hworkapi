package domain.training.competencies

import play.api.libs.json.Json


/**
  * Created by hashcode on 2016/10/17.
  */
case class Competency(compencyId:String, name:String, competencyTypeId:String)

object Competency {
  implicit val compencyFMT = Json.format[Competency]
}
