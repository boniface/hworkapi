package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonIdentity(id: String,
                          personId: String,
                          idType: String,
                          idValue: String,
                          preffered: Boolean,
                          date: Date,
                          state: String)

object PersonIdentity {
  implicit val personIdFmt = Json.format[PersonIdentity]

}
