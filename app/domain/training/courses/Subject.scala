package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class Subject(subjectId: String, topic: String, subjectCode: String, description: String, credit: Int)

object Subject {
  implicit val subjectFmt = Json.format[Subject]
}
