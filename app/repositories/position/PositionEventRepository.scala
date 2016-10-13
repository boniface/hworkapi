package repositories.position

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.PositionEvent

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
class PositionEventRepository extends CassandraTable[PositionEventRepository, PositionEvent] {

  //  positionId: String, id: String, date: Date, event: String

  object positionId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object event extends StringColumn(this)


  override def fromRow(r: Row): PositionEvent = {
    PositionEvent(
      positionId(r),
      id(r),
      date(r),
      event(r)
    )
  }
}

object PositionEventRepository extends PositionEventRepository with RootConnector {
  override lazy val tableName = "posevents"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(event: PositionEvent): Future[ResultSet] = {
    insert
      .value(_.positionId, event.positionId)
      .value(_.id, event.id)
      .value(_.date, event.date)
      .value(_.event, event.event)
      .future()
  }

  def getPositionEvent(positionId: String, id: String): Future[Option[PositionEvent]] = {
    select.where(_.positionId eqs positionId).and(_.id eqs id).one()
  }

  def getPositionEvents(positionId: String): Future[Seq[PositionEvent]] = {
    select.where(_.positionId eqs positionId).fetchEnumerator() run Iteratee.collect()
  }

}
