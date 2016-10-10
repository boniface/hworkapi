package domain.position

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class PositionEvent(positionId: String, positionEventId: String, date: DateTime, event: String)

object PositionEvent {

  implicit val positionFmt = Json.format[PositionEvent]
}
