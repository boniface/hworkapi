package factories.users

import domain.users.UserIdentity
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-28.
  */
class UserIdentityFactory {
  def createPersonIdentity(values: Map[String, String], date: DateTime):UserIdentity={
    UserIdentity(organisationId = values("organisationId"), userId = values("userId"), personIdentityId = values("personIdentityId"), idType = values("idType"),
      idValue = values("idValue"), date = date, state = values("state"))
  }

}
