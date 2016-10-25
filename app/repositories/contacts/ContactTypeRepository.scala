package repositories.contacts

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.contacts.ContactType

import scala.concurrent.Future

sealed class ContactTypeRepository extends CassandraTable[ContactTypeRepository,ContactType]{
  object contactTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): ContactType = {
    ContactType(contactTypeId(r),name(r))
  }
}

object ContactTypeRepository extends ContactTypeRepository with RootConnector {
  override lazy val tableName = "contypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(contypes: ContactType): Future[ResultSet] = {
    insert
      .value(_.contactTypeId, contypes.contactTypeId)
      .value(_.name, contypes.name)
      .future()
  }

  def findById(contactTypeId: String):Future[Option[ContactType]] = {
    select.where(_.contactTypeId eqs contactTypeId).one()
  }
  def findAll: Future[Seq[ContactType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(contactTypeId:String): Future[ResultSet] = {
    delete.where(_.contactTypeId eqs contactTypeId).future()
  }
}
