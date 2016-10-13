package repositories.common.demographics

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.LanguageProficiency

import scala.concurrent.Future

sealed class LanguageProficiencyRepository extends CassandraTable[LanguageProficiencyRepository,LanguageProficiency]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): LanguageProficiency = {
    LanguageProficiency(id(r),name(r),state(r))
  }
}

object LanguageProficiencyRepository extends LanguageProficiencyRepository with RootConnector {
  override lazy val tableName = "langpr"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(langpr: LanguageProficiency): Future[ResultSet] = {
    insert
      .value(_.id, langpr.id)
      .value(_.name, langpr.name)
      .value(_.state, langpr.state)
      .future()
  }

  def findById(id: String):Future[Option[LanguageProficiency]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[LanguageProficiency]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
