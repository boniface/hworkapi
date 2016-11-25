package factories.training.courses

import java.util
import org.joda.time.DateTime

import domain.training.courses.CourseStatus

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseStatusFactory {
  def createCourseStatus(values:util.Map[String, String],dates:DateTime):CourseStatus={
    CourseStatus(courseId = values("courseId"), status = values("status"),date = dates)
  }
}
