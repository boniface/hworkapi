package factories.users

import java.util.Date

import domain.users.{PersonContinuingEducation, UserContact}
import org.joda.time.DateTime

/**
  * Created by Lonwabo on 9/28/2016.
  */
class UserContinuingEducationFactory {

  def createUserContinuingEducation(stringMap: Map[String,String], date:DateTime): PersonContinuingEducation = {
    PersonContinuingEducation(organisationId = stringMap("organisationId"),userId = stringMap("userId"),personContinuingEducationId = stringMap("personContinuingEducationId"),courseId = stringMap("courseId"),competencyEvaluationId = stringMap("competencyEvaluationId"),courseScheduleId = stringMap("courseScheduleId"),date = date,state = stringMap("state"))
  }
}
