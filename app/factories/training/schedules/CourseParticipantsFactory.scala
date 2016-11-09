package factories.training.schedules

import domain.training.schedules.CourseParticipants

/**
  * Created by SONY on 2016-10-19.
  */
class CourseParticipantsFactory
{
  def createCourseParticipants(values: Map[String, String]): CourseParticipants=
  {
    CourseParticipants(scheduledCourseId = values("scheduledCourseId"), userId = values("userId"))
  }
}
