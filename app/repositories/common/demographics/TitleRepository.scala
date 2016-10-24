package repositories.common.demographics

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.Title

import scala.concurrent.Future

sealed class TitleRepository extends CassandraTable[TitleRepository,Title]{
  object titleId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): Title = {
    Title(titleId(r),name(r))
  }
}

object TitleRepository extends TitleRepository with RootConnector {
  override lazy val tableName = "title"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(title: Title): Future[ResultSet] = {
    insert
      .value(_.titleId, title.titleId)
      .value(_.name, title.name)
      .future()
  }

  def findById(titleId: String):Future[Option[Title]] = {
    select.where(_.titleId eqs titleId).one()
  }
  def findAll: Future[Seq[Title]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTitle(titleId: String): Future[Seq[Title]] = {
    select.where(_.titleId eqs titleId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(titleId:String): Future[ResultSet] = {
    delete.where(_.titleId eqs titleId).future()
  }
}
