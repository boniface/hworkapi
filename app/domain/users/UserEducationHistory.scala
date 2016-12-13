package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/12/16.
  */
case class UserEducationHistory(organisationId: String,
                                userId: String,
                                personEducationHistoryId: String,
                                institutionName: String,
                                institutionLocation: String,
                                yearOfGraduation: Int,
                                educationTypeId: String,
                                degreeId: String,
                                notes: String,
                                date: DateTime,
                                state: String)

object UserEducationHistory {
  implicit val personrEduhistFmt = Json.format[UserEducationHistory]

}
