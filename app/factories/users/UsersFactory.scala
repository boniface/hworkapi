package factories.users

import domain.users.User

/**
  * Created by hashcode on 2016/09/25.
  */
class UsersFactory {
  def createUser(stringMap:Map[String,String], boolMap:Map[String,Boolean]) : User ={
    User(organisationId = stringMap("organisationId"),userId = stringMap("userId"),firstName = stringMap("firstName"),middleName = stringMap("middleName"),email = stringMap("email"),lastName = stringMap("lastName"),title = stringMap("title"),authvalue = stringMap("authvalue"),enabled = boolMap("enabled"),accountNonExpired = boolMap("accountNonExpired"),credentialsNonExpired = boolMap("credentialNonExpired"),accountNonLocked = boolMap("accountNonLocked"),state = stringMap("state"))
  }
}
