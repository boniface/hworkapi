package factories.training.courses

import domain.training.courses.Course

/**
  * Created by SONY on 2016-10-18.
  */
class CourseFactory
{
  def createCourse(values: Map[String, String]): Course=
  {
    Course(organisationId = values("organisationId"), courseId = values("courseId"),name = values("name"), courseCategoryId = values("courseCategoryId"),
      courseCode = values("courseCode"), trainingInstitutionId = values("trainingInstitutionId"), courseObjective= values("courseObjective"),
      courseTypeId = values("courseTypeId"), criteriaId = values("criteriaId"), description= values("description"))
  }

}
