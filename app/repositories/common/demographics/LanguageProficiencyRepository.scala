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
  object languageProficiencyId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): LanguageProficiency = {
    LanguageProficiency(languageProficiencyId(r),name(r))
  }
}

object LanguageProficiencyRepository extends LanguageProficiencyRepository with RootConnector {
  override lazy val tableName = "langpr"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(langpr: LanguageProficiency): Future[ResultSet] = {
    insert
      .value(_.languageProficiencyId, langpr.languageProficiencyId)
      .value(_.name, langpr.name)
      .future()
  }

  def findById(languageProficiencyId: String):Future[Option[LanguageProficiency]] = {
    select.where(_.languageProficiencyId eqs languageProficiencyId).one()
  }
  def findAll: Future[Seq[LanguageProficiency]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getLanguageProficiency(languageProficiencyId: String): Future[Seq[LanguageProficiency]] = {
    select.where(_.languageProficiencyId eqs languageProficiencyId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(languageProficiencyId:String): Future[ResultSet] = {
    delete.where(_.languageProficiencyId eqs languageProficiencyId).future()
  }
}
