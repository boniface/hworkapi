package domain.position

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class PositionEvent(positionId: String, positionEventId: String, date: Date, event: String)

object PositionEvent {

  implicit val positionFmt = Json.format[PositionEvent]
}
