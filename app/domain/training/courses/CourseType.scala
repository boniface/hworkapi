package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseType(courseTypeId: String, name: String)

object CourseType {
  implicit val competeFmt = Json.format[CourseType]
}
