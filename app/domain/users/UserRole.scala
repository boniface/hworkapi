package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/10/30.
  */
case class UserRole(organisationId: String,
                    userId: String,
                    roleId: String,
                    state: String,
                    date: DateTime
                     )

object UserRole {
  implicit val personroleFmt = Json.format[UserRole]

}
