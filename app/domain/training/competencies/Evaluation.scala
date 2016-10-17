package domain.training.competencies

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class Evaluation(evaluationId:String, name:String)
object Evaluation{
  implicit val competeFmt = Json.format[Evaluation]
}
