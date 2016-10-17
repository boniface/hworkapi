package domain.training.institutions

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
class TrainingInstitutionLocation(organisationId: String,
                                  TrainingInstitutionLocationId: String,
                                  name: String,
                                  locationTypeId: String,
                                  code: String,
                                  latitude: String,
                                  longitude: String,
                                  state: String,
                                  date: Date)
object TrainingInstitutionLocation{
  implicit val traningInstitutionFmt = Json.format[TrainingInstitutionLocation]

}
