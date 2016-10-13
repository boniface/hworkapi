package factories.users

import domain.users.UserAddress
import org.joda.time.DateTime

/**
  * Created by Lonwabo on 9/28/2016.
  */
class UserAddressFactory {
  def createUserAddress(stringMap: Map[String,String],date:DateTime,detail:Map[String,String]): UserAddress ={
    UserAddress(organisationId = stringMap("organisationId"),userId = stringMap("userId"),userAddressId = stringMap("userAddressId"),addressTypeId = stringMap("userAddressId"),details = detail,date = date,state = stringMap("state"))
  }
}
