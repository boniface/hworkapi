package factories.training.schedules

import domain.training.schedules.ScheduledCourse
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-19.
  */
class ScheduledCourseFactory
{
  def createScheduledCourse(values: Map[String, String], valInt:Int, date: DateTime): ScheduledCourse=
  {
    ScheduledCourse(organisationId = values("organisationId"), courseId = values("courseId"),scheduledCourseId = values("scheduledCourseId"),
      venue= values("venue"), courseCapacity = valInt, creditHours= valInt, startDate = date, endDate = date,
      locationId = values("locationId"), dateScheduled = date)
  }

}
