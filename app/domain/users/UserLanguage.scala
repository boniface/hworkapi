package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class UserLanguage(organisationId: String,
                        userId: String,
                        personLanguageId: String,
                        languageId: String,
                        reading: String,
                        writing: String,
                        speaking: String,
                        date: DateTime,
                        state: String)

object UserLanguage {
  implicit val personLangFmt = Json.format[UserLanguage]

}
