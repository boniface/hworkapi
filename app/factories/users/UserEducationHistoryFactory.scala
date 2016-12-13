package factories.users

import domain.users.UserEducationHistory
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-28.
  */
class UserEducationHistoryFactory {
  def createPersonEducationHistory(values: Map[String, String], date: DateTime, stringMap: Map[String, Int]):UserEducationHistory={
    UserEducationHistory(organisationId = values("UserEducationHistory"), userId = values("userId"), personEducationHistoryId = values("personEducationHistoryId"),
      institutionName = values("institutionName"), institutionLocation = values("institutionLocation"), yearOfGraduation = stringMap("yearOfGraduation"),
      educationTypeId = values("educationTypeId"), degreeId = values("degreeId"), notes = values("notes"), date = date, state = values("state"))
  }

}
