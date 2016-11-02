package factories.training.institutions

import domain.training.institutions.TrainingInstructor

/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstructorFactory
{
  def createTrainingInstructor(values: Map[String, String]): TrainingInstructor=
  {
    TrainingInstructor(organisationId = values("organisationId"), TrainingInstructorId = values("TrainingInstructorId"), title= values("title"),
      firstName= values("firstName"), lastName = values("lastName"), qualification=values("qualification"), areasOfExpertise= values("areasOfExpertise"))
  }

}
