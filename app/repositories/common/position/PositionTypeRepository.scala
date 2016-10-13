package repositories.common.position

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.position.PositionType

import scala.concurrent.Future

sealed class PositionTypeRepository extends CassandraTable[PositionTypeRepository,PositionType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object description extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): PositionType = {
    PositionType(id(r),name(r),state(r))
  }
}

object PositionTypeRepository extends PositionTypeRepository with RootConnector {
  override lazy val tableName = "positiontype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(positionType: PositionType): Future[ResultSet] = {
    insert
      .value(_.id, positionType.id)
      .value(_.name, positionType.name)
      .value(_.state, positionType.state)
      .future()
  }

  def findById(id: String):Future[Option[PositionType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[PositionType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
