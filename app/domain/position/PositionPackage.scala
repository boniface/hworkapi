package domain.position

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/08.
 */
case class PositionPackage(positionId: String,
                           positionPackageId: String,
                           gradeId: String,
                           notchId:String,
                           date: DateTime,
                           state: String)

object PositionPackage {
  implicit val posFmt = Json.format[PositionPackage]
}
