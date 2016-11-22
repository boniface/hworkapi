package factories.organisations

import domain.organisations.OrganisationContact

/**
  * Created by SONY on 2016-10-18.
  */
class OrganisationContactFactory {
  def createOrganisationContact(values: Map[String, String],details: Map[String, String]): OrganisationContact=
  {
    OrganisationContact(organisationId = values("organisationId"), organisationEmail = values("organisationEmail"), organisationContactId = values("organisationContactId"),contactTypeId = values("contactTypeId"), details = details)
  }

}
