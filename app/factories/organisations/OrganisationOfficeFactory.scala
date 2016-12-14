package factories.organisations                                                                                                                                                                                                                         //Xolela Masebeni(213160447) xmasebeni1@gmail.com

import domain.organisations.OrganisationOffice
import org.joda.time.DateTime

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationOfficeFactory {

  def createOrganisationOffice(values:Map[String, String],size:Option[String],date:DateTime):OrganisationOffice={
    OrganisationOffice( organisationId = values("organisationId"),organisationOfficeId = values("organisationOfficeId"),
                        name = values("name"), description = values("description"),active = values("active"),
                        officeTypeId = values("officeTypeId"),state = values("state"), date=date)
  }
}
