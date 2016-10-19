package factories.training.courses

import domain.training.courses.CourseSubjects

/**
  * Created by SONY on 2016-10-18.
  */
class CourseSubjectsFactory
{
  def createCourseSubjects(values: Map[String, String]): CourseSubjects=
  {
    CourseSubjects(organisationId = values("organisationId"), courseId = values("courseId"),subjectId = values("subjectId"))
  }

}
