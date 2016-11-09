package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseCategory(organisationId:String,
                     courseCategoryId: String,
                     name: String)

object CourseCategory {
  implicit val courseCategoryId = Json.format[CourseCategory]
}
