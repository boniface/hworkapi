package domain.organisations

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/03.
  */
case class OrganisationLogo(organisationId: String,
                            organisationLogoId: String,
                            url: String,
                            size: Option[String],
                            description: String,
                            mime: String,
                            date: DateTime)

object OrganisationLogo {

  implicit val companyLogoFmt = Json.format[OrganisationLogo]

}
