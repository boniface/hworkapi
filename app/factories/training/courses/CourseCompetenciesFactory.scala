package factories.training.courses

import domain.training.courses.CourseCompetencies

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseCompetenciesFactory {
  def createCourseCompetencies(values:Map[String, String]):CourseCompetencies={
    CourseCompetencies(organisationId = values("organisationId"), courseId = values("courseId"),compentencyId = values("compentencyId"))
  }
}
