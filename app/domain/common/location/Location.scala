package domain.common.location

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/17.
  */
case class Location(
                   locationId:String,
                   name:String,
                   latititude:String,
                   longitude:String,
                   code:String,
                   locationTypeId:String,
                   locationParentId:Option[String]
                   )
object Location{
  implicit val locationFmt = Json.format[Location]
}
