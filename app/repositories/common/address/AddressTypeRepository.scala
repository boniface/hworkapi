package repositories.common.address

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.address.AddressType

import scala.concurrent.Future

sealed class AddressTypeRepository extends CassandraTable[AddressTypeRepository,AddressType]{
  object addressTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): AddressType = {
    AddressType(addressTypeId(r),name(r))
  }
}

object AddressTypeRepository extends AddressTypeRepository with RootConnector {
  override lazy val tableName = "addtype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(addtype: AddressType): Future[ResultSet] = {
    insert
      .value(_.addressTypeId, addtype.addressTypeId)
      .value(_.name, addtype.name)
      .future()
  }

  def findById(addressTypeId: String):Future[Option[AddressType]] = {
    select.where(_.addressTypeId eqs addressTypeId).one()
  }
  def findAll: Future[Seq[AddressType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getAddressType(addressTypeId: String): Future[Seq[AddressType]] = {
    select.where(_.addressTypeId eqs addressTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(addressTypeId:String): Future[ResultSet] = {
    delete.where(_.addressTypeId eqs addressTypeId).future()
  }
}
