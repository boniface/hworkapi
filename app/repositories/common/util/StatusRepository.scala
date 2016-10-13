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
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object value extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): Status = {
    Status(id(r),name(r),value(r),state(r))
  }
}

object StatusRepository extends StatusRepository with RootConnector {
  override lazy val tableName = "status"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(status: Status): Future[ResultSet] = {
    insert
      .value(_.id, status.id)
      .value(_.name, status.name)
      .value(_.value, status.value)
      .value(_.state, status.state)
      .future()
  }

  def findById(id: String):Future[Option[Status]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[Status]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
