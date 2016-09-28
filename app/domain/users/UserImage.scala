package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/03.
 */
case class PersonImages(organisationId: String,
                        userId: String,
                        personImageId: String,
                        url: String,
                        description: String,
                        mime: String,
                        size: Option[String],
                        date: DateTime)

object PersonImages {
  implicit val companyImagesFmt = Json.format[PersonImages]

}
