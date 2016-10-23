package factories.training.courses

import java.util
import java.util.Date

import domain.training.courses.CourseStatus

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseStatusFactory {
  def createCourseStatus(values:util.Map[String, String],dates:Date):CourseStatus={
    CourseStatus(courseId = values("courseId"), status = values("status"),date = dates)
  }
}
