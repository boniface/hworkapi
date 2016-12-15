package factories.organisations                                                                                                                                                     //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationAddress

/**
  * Created by isiphile on 2016-10-17.
  */
class OrganisationAddressFactory {
  def createOrganisationAddress(values: Map[String, String],details: Map[String, String]): OrganisationAddress=
  {
    OrganisationAddress(organisationId = values("organisationId"), organisationEmail = values("organisationEmail"),
      organisationAddressId = values("organisationAddressId"),organisationLocationId = values("organisationLocationId"),
      addressTypeId= values("addressTypeId"), details = details)
  }

}
