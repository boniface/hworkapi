package factories.training.institutions

import domain.training.institutions.TrainingInstitutionContacts$

/**
  * Created by SONY on 2016-10-19.
  */
class TraningInstitutionContactsFactory
{
  def createTraningInstitutionContacts(values: Map[String, String], details: Map[String, String]): TrainingInstitutionContacts=
  {
    TrainingInstitutionContacts(organisationId = values("organisationId"), traningInstitutionContactsId = values("traningInstitutionContactsId"),
      contactTypeId= values("contactTypeId"), details= details)
  }

}
