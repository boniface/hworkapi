package factories.users

import java.util.Date

import domain.users.UserRole
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-27.
  */
class UserRoleFactory
{
  def createPersonRole(values: Map[String, String]): UserRole=
  {
    UserRole(organisationId = values("organisationId"), userId = values("userId"), roleId = values("roleId"), state = values("state"), date = new DateTime())
  }

}
