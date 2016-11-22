package factories.organisations

import java.util.Date

import domain.organisations.OrganisationLocation
import org.joda.time.DateTime

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationLocationFactory {

  def createOrganisationDepartment(values:Map[String, String],date:DateTime):OrganisationLocation={
    OrganisationLocation(organisationId = values("organisationId"),organisationLocationId = values("organisationLocationId"),name = values("name"), locationTypeId = values("locationTypeId"), code = values("code"),latitude = values("latitude"),longitude = values("longitude"), parentId = values("parentId"), state = values("state"), date=date)
  }
}
