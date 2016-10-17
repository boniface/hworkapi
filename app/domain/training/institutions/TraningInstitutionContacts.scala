package domain.training.institutions

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class TraningInstitutionContacts(organisationId: String,
                                      traningInstitutionContactsId: String,
                                      contactTypeId: String,
                                      details: Map[String, String])

object TraningInstitutionContacts {
  implicit val traningInstitutionContacts = Json.format[TraningInstitutionContacts]

}
