package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/03.
 */
case class PersonImages(company: String,
                        personId: String,
                        id: String,
                        url: String,
                        description: String,
                        mime: String,
                        size: Option[String],
                        date: Date)

object PersonImages {
  implicit val companyImagesFmt = Json.format[PersonImages]

}
