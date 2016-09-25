package domain.common.location

import play.api.libs.json.Json

case class ContactType(id:String, name:String,state:String)

object ContactType{
  implicit val contFmt = Json.format[ContactType]
}