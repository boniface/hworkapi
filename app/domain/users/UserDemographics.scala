package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/11/10.
  */
case class PersonDemographics(organisationId: String,
                              userId: String,
                              personDemographicsId: String,
                              genderId: String,
                              dateOfBirth: DateTime,
                              maritalStatusId: String,
                              numberOfDependencies: Int,
                              date: DateTime,
                              state: String)

object PersonDemographics {
  implicit val personDemoFmt = Json.format[PersonDemographics]

}
