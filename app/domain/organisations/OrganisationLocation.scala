package domain.organisations

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/02/25.
  */
case class OrganisationLocation(organisationId: String,
                                organisationLocationId: String,
                                name: String,
                                locationTypeId: String,
                                code: String,
                                latitude: String,
                                longitude: String,
                                parentId: String,
                                state: String,
                                date: DateTime)

object OrganisationLocation {
  implicit val location = Json.format[OrganisationLocation]

}
