package domain.organisations

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class Office(company: String,
                  id: String,
                  name: String,
                  description: String,
                  active: String,
                  officeTypeId: String,
                  state: String,
                  date: Date
                   )

object Office {
  implicit val officeFmt = Json.format[Office]

}
