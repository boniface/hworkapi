package factories.organisations

import domain.organisations.Organisation
import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/09/25.
  */
class OrganisationFactory {

  def createOrganisation(values: Map[String, String],details: Map[String, String], date: DateTime): Organisation=
  {
    Organisation(organisationId = values("organisationId"), name = values("name"), details = details,adminattached = values("adminattached"), date, state = values("state"))
  }
}
