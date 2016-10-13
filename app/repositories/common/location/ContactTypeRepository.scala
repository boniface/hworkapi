package repositories.common.location

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection

import scala.concurrent.Future

sealed class ContactTypeRepository extends CassandraTable[ContactTypeRepository,ContactType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): ContactType = {
    ContactType(id(r),name(r),state(r))
  }
}

object ContactTypeRepository extends ContactTypeRepository with RootConnector {
  override lazy val tableName = "contypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(contypes: ContactType): Future[ResultSet] = {
    insert
      .value(_.id, contypes.id)
      .value(_.name, contypes.name)
      .value(_.state, contypes.state)
      .future()
  }

  def findById(id: String):Future[Option[ContactType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[ContactType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
