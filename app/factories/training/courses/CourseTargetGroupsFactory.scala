package factories.training.courses

import java.util.Date

import domain.training.courses.CourseTargetGroups

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseTargetGroupsFactory {
  def createCourseTargetGroups(values:Map[String, String]):CourseTargetGroups={
    CourseTargetGroups(organisationId = values("organisationId"), courseId = values("courseId"),targetGroupId = values("targetGroupId"))
  }
}