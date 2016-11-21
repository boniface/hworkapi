package domain.training.courses

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */

case class CourseStatus(courseId: String, status: String, date: DateTime)


object CourseStatus {
  implicit val courseStatus = Json.format[CourseStatus]

}
