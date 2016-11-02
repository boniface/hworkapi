package factories.common.util

import domain.common.util.Mail
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-28.
  */
class MailFactory {
  def createMail(values:Map[String,String],date:DateTime):Mail={

    Mail(mailId = values("mailId"),key = values("key"),value = values("value"),
      host = values("host"),port = values("port"),state = values("state"),date =date)

  }

}
