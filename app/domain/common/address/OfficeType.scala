package domain.common.address

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/25.
  */
case class OfficeType(officeTypeId:String, name:String,code:String,state:String)

object OfficeType{
  implicit val officeTypeFmt = Json.format[OfficeType]
}