package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseTargetGroups(organisationId:String,courseId:String,targetGroupId:String)
object CourseTargetGroups{
  implicit var courseTargetGroupsId = Json.format[CourseTargetGroups]

}
