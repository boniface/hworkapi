package repositories.common.address

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.address.LocationType

import scala.concurrent.Future

sealed class LocationTypeRepository extends CassandraTable[LocationTypeRepository,LocationType]{
  object locationTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object code extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): LocationType = {
    LocationType(locationTypeId(r),name(r),code(r),state(r))
  }
}

object LocationTypeRepository extends LocationTypeRepository with RootConnector {
  override lazy val tableName = "locationtypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(locationtypes: LocationType): Future[ResultSet] = {
    insert
      .value(_.locationTypeId, locationtypes.locationTypeId)
      .value(_.name, locationtypes.name)
      .value(_.code, locationtypes.code)
      .value(_.state, locationtypes.state)
      .future()
  }

  def findById(locationTypeId: String):Future[Option[LocationType]] = {
    select.where(_.locationTypeId eqs locationTypeId).one()
  }
  def findAll: Future[Seq[LocationType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getLocationType(locationTypeId: String): Future[Seq[LocationType]] = {
    select.where(_.locationTypeId eqs locationTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(locationTypeId:String): Future[ResultSet] = {
    delete.where(_.locationTypeId eqs locationTypeId).future()
  }
}
