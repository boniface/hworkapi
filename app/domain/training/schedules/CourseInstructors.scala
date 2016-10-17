package domain.training.schedules

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseInstructors(scheduledCourseId:String,
                             TrainingInstructorId:String)
object CourseInstructors {
 implicit val courseInstructorsFmt =Json.format[CourseInstructors]
}
