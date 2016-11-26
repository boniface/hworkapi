package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/01/07.
  */
case class UserPosition(organisationId: String,
                        userId: String,
                        personPositionId: String,
                        statusDate: DateTime,
                        positionId: String,
                        statusId: String,
                        reason: String
                         )

object UserPosition {
  implicit val personPosFmt = Json.format[UserPosition]

}
