package domain.common.education

import play.api.libs.json.Json

case class Evaluation(evaluationId:String,name:String)

object Evaluation{
  implicit val evalFmt= Json.format[Evaluation]
}