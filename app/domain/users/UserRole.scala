package domain.users

import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/10/30.
  */
case class PersonRole(organisationId: String,
                      userId: String,
                      roleId: String,
                      state: String
                     )

object PersonRole {
  implicit val personroleFmt = Json.format[PersonRole]

}
