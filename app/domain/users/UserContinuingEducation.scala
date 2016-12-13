package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class UserContinuingEducation(organisationId:String,
                                   userId: String,
                                   personContinuingEducationId: String,
                                   courseId: String,
                                   competencyEvaluationId: String,
                                   courseScheduleId: String,
                                   date: DateTime,
                                   state: String)

object UserContinuingEducation {
  implicit val personroleceduFmt = Json.format[UserContinuingEducation]

}
