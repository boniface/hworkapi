package repositories.common.location

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.location.Location
import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-18.
  */
class LocationRepository extends CassandraTable[LocationRepository, Location] {

  object locationId extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)
  object latititude extends StringColumn(this)
  object longitude extends StringColumn(this)
  object code extends StringColumn(this)
  object locationTypeId extends StringColumn(this)
  object locationParentId extends OptionalStringColumn(this)

  override def fromRow(r: Row): Location = {
    Location(locationId(r), name(r), latititude(r), longitude(r), code(r),
      locationTypeId(r),locationParentId = Option("r"))
  }
}

object LocationRepository extends LocationRepository with RootConnector {
  override lazy val tableName = "location"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(location: Location): Future[ResultSet] = {
    insert
      .value(_.locationId, location.locationId)
      .value(_.name, location.name)
      .value(_.latititude, location.latititude)
      .value(_.longitude, location.longitude)
      .value(_.code, location.code)
      .value(_.locationTypeId, location.locationTypeId)
      .value(_.locationParentId, location.locationParentId)
      .future()
  }

  def findById(locationId: String): Future[Option[Location]] = {
    select.where(_.locationId eqs locationId).one()
  }

  def findAll: Future[Seq[Location]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(locationId: String): Future[ResultSet] = {
    delete.where(_.locationId eqs locationId).future()
  }
}
