package domain.common.util

import play.api.libs.json.Json

case class Status(statusId:String,name:String,value:String)

object Status{
  implicit val statFmt = Json.format[Status]
}