package services.common.util.Impl

import repositories.common.util.MailRepository
import com.websudos.phantom.dsl.ResultSet
import domain.common.util.Mail
import services.Service
import services.common.util.MailService

import scala.concurrent.Future

class MailServiceImpl extends MailService with Service{
  override def createOrUpdate(mail: Mail): Future[ResultSet] = {
    MailRepository.save(mail)
  }

  override def getMail(mailId: String): Future[Seq[Mail]] = {
    MailRepository.getMail(mailId)
  }
}
