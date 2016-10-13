package repositories.common.demographics

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.MaritalStatus

import scala.concurrent.Future

sealed class MaritalStatusRepository extends CassandraTable[MaritalStatusRepository,MaritalStatus]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): MaritalStatus = {
    MaritalStatus(id(r),name(r),state(r))
  }
}

object MaritalStatusRepository extends MaritalStatusRepository with RootConnector {
  override lazy val tableName = "maritals"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(maritals: MaritalStatus): Future[ResultSet] = {
    insert
      .value(_.id, maritals.id)
      .value(_.name, maritals.name)
      .value(_.state, maritals.state)
      .future()
  }

  def findById(id: String):Future[Option[MaritalStatus]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[MaritalStatus]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
