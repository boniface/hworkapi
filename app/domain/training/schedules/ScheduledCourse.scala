package domain.training.schedules

import java.util.Date

import org.joda.time.DateTime
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
                           startDate: DateTime,
                           endDate: DateTime,
                           locationId: String,
                           dateScheduled: DateTime
                          )

object ScheduledCourse {
  implicit val scheduledCourseFmt = Json.format[ScheduledCourse]
}
