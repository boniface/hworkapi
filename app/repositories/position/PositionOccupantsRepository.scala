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

  object positionOccupantId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object userId extends StringColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): PositionOccupants = {
    PositionOccupants(
      positionId(r),
      positionOccupantId(r),
      date(r),
      userId(r),
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
      .value(_.positionOccupantId, occupants.positionOccupantId)
      .value(_.date, occupants.date)
      .value(_.userId, occupants.userId)
      .value(_.state, occupants.state)
      .future()
  }

  def getPositionOccupant(positionId: String, positionOccupantId: String): Future[Option[PositionOccupants]] = {
    select.where(_.positionId eqs positionId).and(_.positionOccupantId eqs positionOccupantId).one()
  }

  def getPositionOccupants(positionId: String): Future[Seq[PositionOccupants]] = {
    select.where(_.positionId eqs positionId).fetchEnumerator() run Iteratee.collect()
  }

}
