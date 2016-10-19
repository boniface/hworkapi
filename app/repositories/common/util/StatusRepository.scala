package repositories.common.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.util.Status

import scala.concurrent.Future

sealed class StatusRepository extends CassandraTable[StatusRepository,Status]{
  object statusId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object value extends StringColumn(this)
  override def fromRow(r: Row): Status = {
    Status(statusId(r),name(r),value(r))
  }
}

object StatusRepository extends StatusRepository with RootConnector {
  override lazy val tableName = "status"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(status: Status): Future[ResultSet] = {
    insert
      .value(_.statusId, status.statusId)
      .value(_.name, status.name)
      .value(_.value, status.value)
      .future()
  }

  def findById(statusId: String):Future[Option[Status]] = {
    select.where(_.statusId eqs statusId).one()
  }
  def findAll: Future[Seq[Status]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(statusId:String): Future[ResultSet] = {
    delete.where(_.statusId eqs statusId).future()
  }
}
