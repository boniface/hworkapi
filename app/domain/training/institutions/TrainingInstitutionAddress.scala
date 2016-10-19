package domain.training.institutions

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
class TrainingInstitutionAddress(organisationId: String,
                                 TrainingInstitutionAddressId: String,
                                 trainingInstitutionLocationId:String,
                                 addressTypeId: String,
                                 details: Map[String, String])
object TrainingInstitutionAddress{
  implicit val trainingInstitutionFmt = {
    Json.format[TrainingInstitutionAddress]
  }

}
