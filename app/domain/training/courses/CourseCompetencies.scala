package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class CourseCompetencies(organisationId:String,courseId:String, compentencyId:String)
object CourseCompetencies{
 implicit val courseCompetenciesFmt = Json.format[CourseCompetencies]
}
