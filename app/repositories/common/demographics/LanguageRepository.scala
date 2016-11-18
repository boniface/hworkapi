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
  object languageId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): Language = {
    Language(languageId(r),name(r))
  }
}

object LanguageRepository extends LanguageRepository with RootConnector {
  override lazy val tableName = "language"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(language: Language): Future[ResultSet] = {
    insert
      .value(_.languageId, language.languageId)
      .value(_.name, language.name)
      .future()
  }

  def getLanguageById(languageId: String):Future[Option[Language]] = {
    select.where(_.languageId eqs languageId).one()
  }
  def getLanguages(languageId:String): Future[Seq[Language]] = {
    select.where(_.languageId eqs languageId).fetchEnumerator() run Iteratee.collect()
  }
  def getLanguage(languageId: String): Future[Seq[Language]] = {
    select.where(_.languageId eqs languageId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(languageId:String): Future[ResultSet] = {
    delete.where(_.languageId eqs languageId).future()
  }
}
