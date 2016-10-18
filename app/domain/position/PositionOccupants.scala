package domain.position

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class PositionOccupants( positionId: String,
                              positionOccupantId: String,
                              date: Date,
                              userId:String,
                              state:String)

object PositionOccupants{
  implicit val positionOccupant = Json.format[PositionOccupants]

}
