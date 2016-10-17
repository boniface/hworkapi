package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseSubjects(organisationId:String,courseId:String,subjectId:String)
object CourseSubjects{
 implicit val courseSubjects = Json.format[CourseSubjects]
}
