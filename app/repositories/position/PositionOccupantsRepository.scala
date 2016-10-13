package repositories.position

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.PositionOccupants

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
class PositionOccupantsRepository extends CassandraTable[PositionOccupantsRepository, PositionOccupants] {

  object positionId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object personId extends StringColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): PositionOccupants = {
    PositionOccupants(
      positionId(r),
      id(r),
      date(r),
      personId(r),
      state(r)
    )
  }
}

object PositionOccupantsRepository extends PositionOccupantsRepository with RootConnector {
  override lazy val tableName = "poccupants"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(occupants: PositionOccupants): Future[ResultSet] = {
    insert
      .value(_.positionId, occupants.positionId)
      .value(_.id, occupants.id)
      .value(_.date, occupants.date)
      .value(_.personId, occupants.personId)
      .value(_.state, occupants.state)
      .future()
  }

  def getPositionOccupant(positionId: String, id: String): Future[Option[PositionOccupants]] = {
    select.where(_.positionId eqs positionId).and(_.id eqs id).one()
  }

  def getPositionOccupants(positionId: String): Future[Seq[PositionOccupants]] = {
    select.where(_.positionId eqs positionId).fetchEnumerator() run Iteratee.collect()
  }

}
