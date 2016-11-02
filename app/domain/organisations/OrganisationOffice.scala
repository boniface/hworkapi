package domain.organisations
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/10/30.
  */
case class OrganisationOffice(organisationId: String,
                              organisationOfficeId: String,
                              name: String,
                              description: String,
                              active: String,
                              officeTypeId: String,
                              state: String,
                              date: DateTime
                             )

object OrganisationOffice {
  implicit val officeFmt = Json.format[OrganisationOffice]

}
