package factories.organisations

import java.util.Date

import domain.organisations.OrganisationOffice

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationOfficeFactory {

  def createOrganisationOffice(values:Map[String, String],size:Option[String],date:Date):OrganisationOffice={
    OrganisationOffice(organisationId = values("organisationId"),organisationOfficeId = values("organisationOfficeId"),name = values("name"), description = values("description"),active = values("active"), officeTypeId = values("officeTypeId"),state = values("state"), date=date)
  }
}
