package factories.training.courses

import domain.training.courses.CourseTargetGroups

/**
  * Created by SONY on 2016-10-18.
  */
class CourseTargetGroupsFactory
{
  def createCourseTargetGroups(values: Map[String, String]): CourseTargetGroups=
  {
    CourseTargetGroups(organisationId = values("organisationId"), courseId = values("courseId"),targetGroupId = values("targetGroupId"))
  }

}
