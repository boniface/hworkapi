package factories.training.institutions

import domain.training.institutions.TrainingInstitution

/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionFactory
{
  def createTrainingInstitution(values: Map[String, String]): TrainingInstitution=
  {
    TrainingInstitution(organisationId = values("organisationId"), trainingInstitutionId = values("trainingInstitutionId"), name= values("name"),
      email= values("email"), status = values("status"))
  }
}
