package repositories.common.demographics

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.Language

import scala.concurrent.Future

sealed class LanguageRepository extends CassandraTable[LanguageRepository,Language]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object description extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): Language = {
    Language(id(r),name(r),state(r))
  }
}

object LanguageRepository extends LanguageRepository with RootConnector {
  override lazy val tableName = "language"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(language: Language): Future[ResultSet] = {
    insert
      .value(_.id, language.id)
      .value(_.name, language.name)
      .value(_.state, language.state)
      .future()
  }

  def findById(id: String):Future[Option[Language]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[Language]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
