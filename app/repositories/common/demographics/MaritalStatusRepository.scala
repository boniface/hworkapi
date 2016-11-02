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
  object maritalStatusId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): MaritalStatus = {
    MaritalStatus(maritalStatusId(r),name(r))
  }
}

object MaritalStatusRepository extends MaritalStatusRepository with RootConnector {
  override lazy val tableName = "maritals"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(maritals: MaritalStatus): Future[ResultSet] = {
    insert
      .value(_.maritalStatusId, maritals.maritalStatusId)
      .value(_.name, maritals.name)
      .future()
  }

  def findById(maritalStatusId: String):Future[Option[MaritalStatus]] = {
    select.where(_.maritalStatusId eqs maritalStatusId).one()
  }
  def findAll: Future[Seq[MaritalStatus]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getMaritalStatus(maritalStatusId: String): Future[Seq[MaritalStatus]] = {
    select.where(_.maritalStatusId eqs maritalStatusId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(maritalStatusId:String): Future[ResultSet] = {
    delete.where(_.maritalStatusId eqs maritalStatusId).future()
  }
}
