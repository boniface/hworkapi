package domain.training.institutions

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class TrainingInstructor(organisationId: String,
                              TrainingInstructorId: String,
                              title: String,
                              firstName: String,
                              lastName: String,
                              qualification: String,
                              areasOfExpertise: String
                             )

object TrainingInstructor {
  implicit val trainingInstructorFmt = Json.format[TrainingInstructor]

}
