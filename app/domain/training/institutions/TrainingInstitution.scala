package domain.training.institutions

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class TrainingInstitution(organisationId:String,
                               trainingInstitutionId:String,
                               name:String,
                               email:String,
                               status:String
                               )

object TrainingInstitution{
  implicit val trainingInstitutionFmt = Json.format[TrainingInstitution]

}
