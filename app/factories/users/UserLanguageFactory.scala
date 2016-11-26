package factories.users

import domain.users.UserLanguage
import org.joda.time.DateTime


/**
  * Created by SONY on 2016-09-27.
  */
class UserLanguageFactory
{
  def createPersonLanguage(values: Map[String, String], date: DateTime): UserLanguage=
  {
    UserLanguage(organisationId = values("organisationId"), userId = values("userId"), personLanguageId = values("personLanguageId"),
      languageId = values("languageId"), reading = values("reading"), writing = values("writing"), speaking = values("speaking"), date = date,
      state = values("state"))
  }

}
