package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class Criteria(criteriaId:String, name:String)
object Criteria{
  implicit val criteriaFmt = Json.format[Criteria]
}
