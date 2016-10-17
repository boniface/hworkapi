package domain.training.schedules

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class ScheduledCourse(organisationId: String,
                           courseId: String,
                           scheduledCourseId: String,
                           venue: String,
                           courseCapacity: Int,
                           creditHours: Int,
                           startDate: Date,
                           endDate: Date,
                           locationId: String,
                           dateScheduled: Date
                          )

object ScheduledCourse {
  implicit val scheduledCourseFmt = Json.format[ScheduledCourse]
}
