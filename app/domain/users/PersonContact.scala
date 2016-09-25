package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonContact(id: String,
                         personId: String,
                         addressTypeId: String,
                         contactValue: String,
                         status: String,
                         date: Date,
                         state: String)

object PersonContact {
  implicit val personcontactFmt = Json.format[PersonContact]
}
