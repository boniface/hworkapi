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
  object positionTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): PositionType = {
    PositionType(positionTypeId(r),name(r))
  }
}

object PositionTypeRepository extends PositionTypeRepository with RootConnector {
  override lazy val tableName = "positiontype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(positionType: PositionType): Future[ResultSet] = {
    insert
      .value(_.positionTypeId, positionType.positionTypeId)
      .value(_.name, positionType.name)
      .future()
  }

  def findById(positionTypeId: String):Future[Option[PositionType]] = {
    select.where(_.positionTypeId eqs positionTypeId).one()
  }
  def findAll: Future[Seq[PositionType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPositionType(positionTypeId: String): Future[Seq[PositionType]] = {
    select.where(_.positionTypeId eqs positionTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(positionTypeId:String): Future[ResultSet] = {
    delete.where(_.positionTypeId eqs positionTypeId).future()
  }
}
