package repositories.common.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.util.Mail

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/28.
 */


class MailRepository extends CassandraTable[MailRepository, Mail] {

  object mailId extends StringColumn(this) with PartitionKey[String]

  object key extends StringColumn(this)

  object value extends StringColumn(this)

  object host extends StringColumn(this)

  object port extends StringColumn(this)

  object state extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): Mail = {
    Mail(mailId(r), key(r), value(r), host(r), port(r),state(r),date(r))
  }
}

object MailRepository extends MailRepository with RootConnector {
  override lazy val tableName = "mail"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(mail: Mail): Future[ResultSet] = {
    insert
      .value(_.mailId, mail.mailId)
      .value(_.key, mail.key)
      .value(_.value, mail.value)
      .value(_.host, mail.host)
      .value(_.port, mail.port)
      .value(_.state, mail.state)
      .value(_.date, mail.date)
      .future()
  }

  def findById(mailId: String): Future[Option[Mail]] = {
    select.where(_.mailId eqs mailId).one()
  }

  def findAll: Future[Seq[Mail]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

}

