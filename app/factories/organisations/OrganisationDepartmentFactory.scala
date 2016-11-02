package factories.organisations

import domain.organisations.OrganisationDepartment
import org.joda.time.DateTime
/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationDepartmentFactory {

  def createOrganisationDepartment(values:Map[String, String],date:DateTime):OrganisationDepartment={
    OrganisationDepartment(organisationId = values("organisationId"),organisationDepartmentId = values("organisationDepartmentId"),
      name = values("name"),description = values("description"),active = values("active"),state = values("state"),date=date)
  }

}
