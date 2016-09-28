package factories.common.util

import domain.common.util.Mail

class MailFactory {
  def createMail(values:Map[String, String],date:Date):Mail={
 Mail(mailId = values("mailId"),
          key = values("key"),
          value = values("value"),
          host = values("host"),
          port = values("port"),
          state = values("state"),
          date=Date)
  }

}