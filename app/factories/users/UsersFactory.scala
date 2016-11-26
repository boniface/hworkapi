package factories.users

import domain.users.User

/**
  * Created by hashcode on 2016/09/25.
  */
class UsersFactory {
  def createUser(values: Map[String, String], valBool: Map[String, Boolean]): User=
    User(organisationId = values("organisationId"), userId = values("userId"), firstName= values("firstName"), middleName= Some(values("middleName")),
      email= values("email"), lastName= values("lastName"), title= values("title"), authvalue= values("authvalue"), enabled= valBool("enabled"),
      accountNonExpired= valBool("accountNonExpired"), credentialsNonExpired= valBool("credentialsNonExpired"),
      accountNonLocked= valBool("accountNonLocked"),state= values("state")
    )
}
