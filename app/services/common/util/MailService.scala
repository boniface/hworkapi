package services.common.util

import com.websudos.phantom.dsl._
import domain.common.util.Mail
import services.common.util.Impl.MailServiceImpl

import scala.concurrent.Future


trait MailService {

  def createOrUpdate(mail: Mail): Future[ResultSet]
  def getMail(mailId: String): Future[Seq[Mail]]

}

object MailService{
  def apply: MailService = new MailServiceImpl()
}
