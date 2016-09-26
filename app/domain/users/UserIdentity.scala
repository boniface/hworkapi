package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2015/12/16.
  */
case class PersonIdentity(organisationId: String,
                          userId: String,
                          personIdentityId: String,
                          idType: String,
                          idValue: String,
                          date: Date,
                          state: String)

object PersonIdentity {
  implicit val personIdFmt = Json.format[PersonIdentity]

}
