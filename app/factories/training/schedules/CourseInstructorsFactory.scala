package factories.training.schedules

import domain.training.schedules.CourseInstructors

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseInstructorsFactory {
  def createCourseInstructors(values:Map[String, String]):CourseInstructors={
    CourseInstructors(scheduledCourseId = values("scheduledCourseId"), TrainingInstructorId = values("TrainingInstructorId"))
  }
}
