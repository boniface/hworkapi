package factories.training.courses

import domain.training.courses.CourseType

/**
  * Created by SONY on 2016-10-18.
  */
class CourseTypeFactory
{
  def createCourseType(values: Map[String, String]): CourseType=
  {
    CourseType(courseTypeId = values("targetGroupId"), name = values("name"))
  }

}
