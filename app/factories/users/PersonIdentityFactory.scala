package factories.users

import domain.users.PersonIdentity
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-28.
  */
class PersonIdentityFactory {
  def createPersonIdentity(values: Map[String, String], date: DateTime):PersonIdentity={
    PersonIdentity(organisationId = values("organisationId"), userId = values("userId"), personIdentityId = values("personIdentityId"), idType = values("idType"),
      idValue = values("idValue"), date = date, state = values("state"))
  }

}
