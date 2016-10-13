package repositories.common.location

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection

import scala.concurrent.Future

sealed class AddressTypeRepository extends CassandraTable[AddressTypeRepository,AddressType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): AddressType = {
    AddressType(id(r),name(r),state(r))
  }
}

object AddressTypeRepository extends AddressTypeRepository with RootConnector {
  override lazy val tableName = "addtype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(addtype: AddressType): Future[ResultSet] = {
    insert
      .value(_.id, addtype.id)
      .value(_.name, addtype.name)
      .value(_.state, addtype.state)
      .future()
  }

  def findById(id: String):Future[Option[AddressType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[AddressType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
