package domain.training.courses

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class Course(organisationId:String,
                  courseId:String,
                  name:String,
                  courseCategoryId: String,
                  courseCode:String,
                  trainingInstitutionId:String,
                  courseObjective:String,
                  courseTypeId:String,
                  criteriaId: String,
                  description:String
                 )
object Course{
  implicit val courseFmt = Json.format[Course]

}
