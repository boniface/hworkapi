package domain.common.education

import play.api.libs.json.Json

case class Evaluation(id:String,name:String,state:String)

object Evaluation{
  implicit val evalFmt= Json.format[Evaluation]
}