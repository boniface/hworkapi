package factories.training.courses

import java.util.Date

import domain.training.courses.CourseSubjects

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseSubjectsFactory {
  def createCourseSubjects(values:Map[String, String]):CourseSubjects={
    CourseSubjects(organisationId = values("organisationId"), courseId = values("courseId"),subjectId = values("subjectId"))
  }
}