package factories.training.courses

import domain.training.courses.CourseCompetencies

/**
  * Created by SONY on 2016-10-18.
  */
class CourseCompetenciesFactory
{
  def createCourseCompetencies(values: Map[String, String]): CourseCompetencies=
  {
    CourseCompetencies(organisationId = values("organisationId"), courseId = values("courseId"),compentencyId = values("compentencyId"))
  }

}
