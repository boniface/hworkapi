package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserAddress(userId: String,
                       userAddressId:String,
                       addressTypeId:String,
                       details:Map[String,String],
                       date: DateTime,
                       state: String)

object UserAddress {
   implicit val addresstypeFmt = Json.format[UserAddress]
}