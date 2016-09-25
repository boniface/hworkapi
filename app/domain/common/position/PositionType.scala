package domain.common.position

import play.api.libs.json.Json

case class PositionType(positionTypeId:String, name:String)

object PositionType{
  implicit val posFmt = Json.format[PositionType]
}