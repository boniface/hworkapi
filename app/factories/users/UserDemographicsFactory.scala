package factories.users

import java.util.Date

import domain.users.{PersonContinuingEducation, PersonDemographics}
import org.joda.time.DateTime

/**
  * Created by Lonwabo on 9/28/2016.
  */
class UserDemographicsFactory {
  def createUserContinuingEducation(stringMap: Map[String,String], dateMap:Map[String,DateTime], numberOfDependencies: Int): PersonDemographics = {
    PersonDemographics(organisationId = stringMap("organisationId"),userId = stringMap("userId"),personDemographicsId = stringMap("personDemographicsId"),genderId = stringMap("genderId"),dateOfBirth = dateMap("dateOfBirth"),maritalStatusId = stringMap("maritalStatusId"),numberOfDependencies = numberOfDependencies,date = dateMap("date"),state = stringMap("state"))
  }
}

