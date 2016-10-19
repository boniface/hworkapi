package factories.organisations

import domain.organisations.OrganisationAddress

/**
  * Created by SONY on 2016-10-18.
  */
class OrganisationAddressFactory {
  def createOrganisationAddress(values: Map[String, String],details: Map[String, String]): OrganisationAddress=
  {
    OrganisationAddress(organisationId = values("organisationId"), organisationEmail = values("organisationEmail"),
      organisationAddressId = values("organisationAddressId"),organisationLocationId = values("organisationLocationId"),
      addressTypeId= values("addressTypeId"), details = details)
  }

}
