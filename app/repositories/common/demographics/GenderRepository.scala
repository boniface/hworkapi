package repositories.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.Gender

import scala.concurrent.Future

sealed class GenderRepository extends CassandraTable[GenderRepository,Gender]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): Gender = {
    Gender(id(r),name(r),state(r))
  }
}

object GenderRepository extends GenderRepository with RootConnector {
  override lazy val tableName = "gender"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(gender: Gender): Future[ResultSet] = {
    insert
      .value(_.id, gender.id)
      .value(_.name, gender.name)
      .value(_.state, gender.state)
      .future()
  }

  def findById(id: String):Future[Option[Gender]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[Gender]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
