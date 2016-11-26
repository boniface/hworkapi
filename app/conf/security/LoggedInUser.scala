package conf.security

//import domain.people.User
import domain.users.User
import services.common.util.TokenService
import services.users.UserService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/06.
  */
object LoggedInUser {
  def user(token: String): Future[Option[User]] = {
    val email = TokenService.apply().getEmail(token)
    val orgCode = TokenService.apply().getOrgCode(token)
    UserService.apply.getUser(orgCode, email)
  }
}
