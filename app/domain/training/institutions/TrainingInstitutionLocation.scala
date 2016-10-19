package domain.training.institutions

import org.joda.time.DateTime
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
                                  date: DateTime)
object TrainingInstitutionLocation{
  implicit val traningInstitutionFmt = Json.format[TrainingInstitutionLocation]

}
