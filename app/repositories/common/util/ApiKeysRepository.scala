package repositories.common.util

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.util.ApiKeys

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/08/29.
  */
class ApiKeysRepository extends CassandraTable[ApiKeysRepository, ApiKeys] {

  object id extends StringColumn(this) with PartitionKey[String]

  object value extends StringColumn(this)

  override def fromRow(row: Row): ApiKeys = {
    ApiKeys(
      id(row),
      value(row)
    )
  }
}

object ApiKeysRepository extends ApiKeysRepository with RootConnector {
  override lazy val tableName = "apikeys"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(key: ApiKeys): Future[ResultSet] = {
    insert
      .value(_.id, key.id)
      .value(_.value, key.value)
      .future()
  }

  def getKeyById(id: String): Future[Option[ApiKeys]] = {
    select.where(_.id eqs id).one()
  }

  def getAllkeys: Future[Seq[ApiKeys]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteKey(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
