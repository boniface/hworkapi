package factories.organisations

import domain.organisations.OrganisationAddress

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationAddressFactory {

  def createOrganisationAddress(values: Map[String, String]): OrganisationAddress=
  {
    OrganisationAddress(organisationId = values("organisationId"), organisationEmail = values("organisationEmail"), organisationAddressId = values("organisationAddressId"),organisationLocationId = values("organisationLocationId"), addressTypeId = values("addressTypeId"), details = details)
  }
}
