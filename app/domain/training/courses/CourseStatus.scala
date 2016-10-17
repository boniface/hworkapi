package domain.training.courses

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
class CourseStatus(courseId: String, status: String, date: Date)

object CourseStatus {
  implicit val courseStatus = Json.format[CourseStatus]

}
