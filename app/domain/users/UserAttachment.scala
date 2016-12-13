package domain.users


import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/03.
  */
case class UserAttachment(organisationId: String,
                          userId: String,
                          personAttachmentId: String,
                          url: String,
                          description: String,
                          mime: String,
                          date: DateTime
                           )

object UserAttachment {
  implicit val personAttachmentFmt = Json.format[UserAttachment]
}
