package factories.common.contacts

import domain.common.contacts.ContactType

class ContactTypeFactory {
  def createContactType(values:Map[String, String]):ContactType={
 ContactType(contactTypeId = values("contactTypeId"),name = values("name"))
  }

}