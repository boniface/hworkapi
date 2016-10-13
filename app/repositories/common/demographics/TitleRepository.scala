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
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): Title = {
    Title(id(r),name(r),state(r))
  }
}

object TitleRepository extends TitleRepository with RootConnector {
  override lazy val tableName = "title"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(title: Title): Future[ResultSet] = {
    insert
      .value(_.id, title.id)
      .value(_.name, title.name)
      .value(_.state, title.state)
      .future()
  }

  def findById(id: String):Future[Option[Title]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[Title]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
