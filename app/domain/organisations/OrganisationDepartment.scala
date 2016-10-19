package domain.organisations

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/10/30.
  */
case class OrganisationDepartment(organisationId: String,
                                  organisationDepartmentId: String,
                                  name: String,
                                  description: String,
                                  active: String,
                                  state: String,
                                  date: DateTime
                                 )

object OrganisationDepartment {
  implicit val departmentFmt = Json.format[OrganisationDepartment]
}
