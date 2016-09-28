package factories.users

import java.util.Date

import domain.users.PersonRole

/**
  * Created by SONY on 2016-09-27.
  */
class PersonRoleFactory
{
  def createPersonRole(values: Map[String, String]): PersonRole=
  {
    PersonRole(organisationId = values("organisationId"), userId = values("userId"), roleId = values("roleId"), state = values("state"))
  }

}
