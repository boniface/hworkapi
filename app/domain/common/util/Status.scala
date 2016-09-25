package domain.common.util

import play.api.libs.json.Json

case class Status(id:String,name:String,value:String,state:String)

object Status{
  implicit val statFmt = Json.format[Status]
}