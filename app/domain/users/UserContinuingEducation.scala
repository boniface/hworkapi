package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonContinuingEducation(organisationId:String,
                                     userId: String,
                                     personContinuingEducationId: String,
                                     courseId: String,
                                     competencyEvaluationId: String,
                                     courseScheduleId: String,
                                     date: Date,
                                     state: String)

object PersonContinuingEducation {
  implicit val personroleceduFmt = Json.format[PersonContinuingEducation]

}
