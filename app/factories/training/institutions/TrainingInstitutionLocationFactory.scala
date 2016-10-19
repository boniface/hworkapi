package factories.training.institutions

import domain.training.institutions.TrainingInstitutionLocation
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionLocationFactory
{
  def createTrainingInstitutionLocation(values: Map[String, String], date: DateTime): TrainingInstitutionLocation=
  {
    TrainingInstitutionLocation(organisationId = values("organisationId"), TrainingInstitutionLocationId = values("TrainingInstitutionLocationId"),
      name= values("name"), locationTypeId = values("locationTypeId"), code = values("code"),
      latitude= values("latitude"), longitude = values("longitude"), date = date)
  }

}
