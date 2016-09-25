package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonAddress( userId: String,
                          personAddressId: String,
                          description: String,
                          postalCode: String,
                          addressTypeId: String,
                          date: Date,
                          state: String)

object PersonAddress {
  implicit val personAddressFmt = Json.format[PersonAddress]
}
