package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserContact(organisationId: String,
                       userId: String,
                       userContactId: String,
                       contactTypeId: String,
                       details: Map[String, String],
                       date: DateTime,
                       state: String)

object UserContact {
  implicit val userContactsFmt = Json.format[UserContact]
}
