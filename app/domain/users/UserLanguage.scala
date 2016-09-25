package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonLanguage(id: String,
                          personId: String,
                          languageId: String,
                          reading: String,
                          writing: String,
                          speaking: String,
                          date: Date,
                          state: String)

object PersonLanguage {
  implicit val personLangFmt = Json.format[PersonLanguage]

}
