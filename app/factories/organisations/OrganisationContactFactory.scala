package factories.organisations                                                                                                                                                                                                                                                                                                                                           //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationContact

/**
  * Created by Isiphile on 2016-10-17.
  */
class OrganisationContactFactory {
  def createOrganisationContact(values: Map[String, String],details: Map[String, String]): OrganisationContact=
  {
    OrganisationContact(organisationId = values("organisationId"), organisationEmail = values("organisationEmail"), organisationContactId = values("organisationContactId"),contactTypeId = values("contactTypeId"), details = details)
  }

}
