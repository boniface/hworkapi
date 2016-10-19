package factories.training.institutions

import domain.training.institutions.TrainingInstitutionAddress

/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionAddressFactory
{
  def createTrainingInstitutionAddress(values: Map[String, String], details: Map[String, String]): TrainingInstitutionAddress=
  {
    TrainingInstitutionAddress(organisationId = values("organisationId"), TrainingInstitutionAddressId = values("TrainingInstitutionAddressId"),
      trainingInstitutionLocationId= values("trainingInstitutionLocationId"), addressTypeId= values("addressTypeId"), details = details)
  }

}
