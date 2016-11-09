package factories.training.institutions

import domain.training.institutions.TraningInstitutionContacts

/**
  * Created by SONY on 2016-10-19.
  */
class TraningInstitutionContactsFactory
{
  def createTraningInstitutionContacts(values: Map[String, String], details: Map[String, String]): TraningInstitutionContacts=
  {
    TraningInstitutionContacts(organisationId = values("organisationId"), traningInstitutionContactsId = values("traningInstitutionContactsId"),
      contactTypeId= values("contactTypeId"), details= details)
  }

}
