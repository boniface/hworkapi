package domain.position

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/08.
 */
case class PositionPackage(positionId: String,
                           id: String,
                           gradeId: String,
                           notchId:String,
                           date: Date,
                           state: String)

object PositionPackage {
  implicit val posFmt = Json.format[PositionPackage]
}
