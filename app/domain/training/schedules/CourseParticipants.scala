package domain.training.schedules

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseParticipants(scheduledCourseId: String,
                              userId: String)

object CourseParticipants {
  implicit val courseInstructors = Json.format[CourseInstructors]

}
