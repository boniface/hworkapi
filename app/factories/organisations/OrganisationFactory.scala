package factories.organisations

import domain.organisations.Organisation
import play.data.format.Formats.DateTime
import java.util.Date

/**
  * Created by hashcode on 2016/09/25.
  */
class OrganisationFactory {

  def createOrganisation(values: Map[String, String],details: Map[String, String], date: DateTime): Organisation=
  {
    Organisation(organisationId = values("organisationId"), name = values("name"), details = details,adminattached = values("adminattached"), date = Date, state = values("state"))
  }
}
