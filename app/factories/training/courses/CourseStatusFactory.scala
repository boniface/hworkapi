package factories.training.courses

import domain.training.courses.CourseStatus
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-18.
  */
class CourseStatusFactory
{
  def createCourseCompetencies(values: Map[String, String], date: DateTime): CourseStatus=
  {
    CourseStatus(courseId = values("courseId"), status = values("status"), date = date)
  }

}
