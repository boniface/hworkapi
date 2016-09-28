package factories.users

import domain.users.PersonEducationHistory
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-28.
  */
class PersonEducationHistoryFactory {
  def createPersonEducationHistory(values: Map[String, String], date: DateTime, stringMap: Map[String, Int]):PersonEducationHistory={
    PersonEducationHistory(organisationId = values("PersonEducationHistory"), userId = values("userId"), personEducationHistoryId = values("personEducationHistoryId"),
      institutionName = values("institutionName"), institutionLocation = values("institutionLocation"), yearOfGraduation = stringMap("yearOfGraduation"),
      educationTypeId = values("educationTypeId"), degreeId = values("degreeId"), notes = values("notes"), date = date, state = values("state"))
  }

}
