package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/03.
  */
case class PersonAttachment(organisationId: String,
                            userId: String,
                            personAttachmentId: String,
                            url: String,
                            description: String,
                            mime: String,
                            date: Date
                           )

object PersonAttachment {
  implicit val personAttachmentFmt = Json.format[PersonAttachment]
}
