package factories.training.courses

import domain.training.courses.Course

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseFactory {
  def createCourse(values:Map[String, String]):Course={
    Course(organisationId = values("organisationId"), courseId = values("courseId"), name = values("name"),
      courseCategoryId = values("courseCategoryId"), courseCode = values("courseCode"), trainingInstitutionId = values("trainingInstitutionId"),
      courseObjective = values("courseObjective"), courseTypeId = values("courseTypeId"), criteriaId = values("criteriaId"),description = values("description"))
  }
}
