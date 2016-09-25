package domain.position

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class PositionDesignation(positionId: String,
                               id: String,
                               date: Date,
                               designationId: String,
                               state: String)

object PositionDesignation {
  implicit val postionFmt = Json.format[PositionDesignation]
}
