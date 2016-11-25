package factories.training.schedules

import org.joda.time.DateTime

import domain.training.schedules.ScheduledCourse

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object ScheduledCourseFactory {
  def createScheduledCourse(values:Map[String, String],capacity:Int,hours:Int,start:DateTime,end:DateTime,sched:DateTime):ScheduledCourse={
    ScheduledCourse(organisationId = values("organisationId"), courseId = values("courseId"),scheduledCourseId = values("scheduledCourseId"),
      venue = values("venue"), courseCapacity =capacity,creditHours = hours,
      startDate = start, endDate = end,locationId = values("locationId"),dateScheduled = sched)
  }
}
