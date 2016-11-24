package domain.training.institutions

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class TrainingInstitutionContacts(organisationId: String,
                                       trainingInstitutionContactsId: String,
                                       contactTypeId: String,
                                       details: Map[String, String])

object TrainingInstitutionContacts {
  implicit val trainingInstitutionContacts = Json.format[TrainingInstitutionContacts]

}
