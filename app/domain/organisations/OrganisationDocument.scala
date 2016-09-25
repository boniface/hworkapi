package domain.organisations

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/05.
  *
  */
case class OrganisationDocuments(organisationId: String,
                                 organisationDocumentsId: String,
                                 description: String,
                                 url: String,
                                 mime: String,
                                 date: Date,
                                 permission: Set[String],
                                 state: String)

object OrganisationDocuments {
  implicit val companyDocFmt = Json.format[OrganisationDocuments]
}
