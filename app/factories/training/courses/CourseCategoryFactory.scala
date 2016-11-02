package factories.training.courses

import domain.training.courses.CourseCategory

/**
  * Created by SONY on 2016-10-18.
  */
class CourseCategoryFactory {
  def createCourseCategory(values: Map[String, String]): CourseCategory=
  {
    CourseCategory(organisationId = values("organisationId"), courseCategoryId = values("courseCategoryId"),name = values("name"))
  }
}