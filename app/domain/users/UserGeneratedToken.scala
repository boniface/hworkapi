package domain.users

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/02.
  */
case class UserGeneratedToken(token: String,
                              status: String,
                              message: String,
                              organisations: String
                             )

object UserGeneratedToken {
  implicit val tokenForm = Json.format[UserGeneratedToken]

}
