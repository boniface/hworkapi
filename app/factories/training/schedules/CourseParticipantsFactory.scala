package factories.training.schedules

import domain.training.schedules.CourseParticipants

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseParticipantsFactory {
  def createCourseParticipants(values:Map[String, String]):CourseParticipants={
    CourseParticipants(scheduledCourseId = values("scheduledCourseId"), userId = values("userId"))
  }
}
