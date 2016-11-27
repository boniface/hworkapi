package domain.users

import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/10/30.
  */
case class User(  organisationId: String,
                  userId: String,
                  firstName: String,
                  middleName: Option[String],
                  email: String,
                  lastName: String,
                  title: String,
                  authvalue: String,
                  enabled: Boolean,
                  accountNonExpired: Boolean,
                  credentialsNonExpired: Boolean,
                  accountNonLocked: Boolean,
                  state: String
                 )

object User {
  implicit val personFmt = Json.format[User]
}
