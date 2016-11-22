package domain.position

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class PositionDesignation(positionId: String,
                               positionDesignationId: String,
                               date: DateTime,
                               designationId: String,
                               state: String)

object PositionDesignation {
  implicit val postionFmt = Json.format[PositionDesignation]
}
