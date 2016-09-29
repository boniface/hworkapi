package factories.common.demographics

import domain.common.demographics.Role

class RoleFactory {
  def createRole(values:Map[String, String]):Role={
 Role(roleId = values("RoleId"),name = values("name"),description =values("description"))
  }

}