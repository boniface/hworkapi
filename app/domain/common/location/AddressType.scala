
package domain.common.location

import play.api.libs.json.Json

case class AddressType(id:String, name:String,state:String)

object AddressType{
  implicit val addreFmt = Json.format[AddressType]
}

