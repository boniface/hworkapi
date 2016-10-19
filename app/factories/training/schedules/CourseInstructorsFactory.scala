package factories.training.schedules

import domain.training.schedules.CourseInstructors

/**
  * Created by SONY on 2016-10-19.
  */
class CourseInstructorsFactory
{
  def createCourseInstructors(values: Map[String, String]): CourseInstructors=
  {
    CourseInstructors(scheduledCourseId = values("scheduledCourseId"), TrainingInstructorId = values("TrainingInstructorId"))
  }
}
