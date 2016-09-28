package factories.users

import java.util.Date

import domain.users.PersonAttachment
import org.joda.time.DateTime

/**
  * Created by Lonwabo on 9/28/2016.
  */
class UserAttachmentFactory {
  def createUserAttachment(stringMap:Map[String,String],date:DateTime): PersonAttachment={
    PersonAttachment(organisationId = stringMap("organisationId"),userId = stringMap("userId"),personAttachmentId = stringMap("personAttachmentId"),url = stringMap("url"),description = stringMap("description"),mime = stringMap("mime"),date = date)
  }
 }
