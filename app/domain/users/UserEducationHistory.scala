package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonEducationHistory(id: String,
                                  personId: String,
                                  institutionName: String,
                                  institutionLocation: String,
                                  yearOfGraduation: Int,
                                  educationTypeId: String,
                                  degreeId: String,
                                  notes: String,
                                  date: Date,
                                  state: String)

object PersonEducationHistory {
  implicit val personrEduhistFmt = Json.format[PersonEducationHistory]

}
