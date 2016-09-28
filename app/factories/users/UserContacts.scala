package factories.users

import javax.jws.soap.SOAPBinding.Use

import domain.users.UserContact
import org.joda.time.DateTime

/**
  * Created by Lonwabo on 9/28/2016.
  */
class UserContacts {
  def createUserContact(stringMap: Map[String,String], date:DateTime, detail: Map[String,String]): UserContact = {
    UserContact(organisationId = stringMap("organisationId"),userId = stringMap("userId"),userContactId = stringMap("userContactId"),contactTypeId = stringMap("contactTypeId"),details = detail,date = date,state = stringMap("state"))
  }
}
