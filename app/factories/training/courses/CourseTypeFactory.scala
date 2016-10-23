package factories.training.courses

import java.util.Date

import domain.training.courses.CourseType

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseTypeFactory {
  def createCourseType(values:Map[String, String]):CourseType={
    CourseType(courseTypeId = values("courseTypeId"), name = values("name"))
  }
}