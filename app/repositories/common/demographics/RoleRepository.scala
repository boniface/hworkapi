package repositories.common.demographics

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.Role

import scala.concurrent.Future

/**
  * Created by hashcode on 2015/10/31.
  */
//id: String, name: String, description: String
sealed class RoleRepository extends CassandraTable[RoleRepository, Role] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object description extends StringColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): Role = {
    Role(id(r), name(r), description(r), state(r))
  }
}

object RoleRepository extends RoleRepository with RootConnector {
  override lazy val tableName = "roles"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(role: Role): Future[ResultSet] = {
    insert
      .value(_.id, role.id)
      .value(_.name, role.name)
      .value(_.description, role.description)
      .value(_.state, role.state)
      .future()
  }

  def findById(id: String): Future[Option[Role]] = {
    select.where(_.id eqs id).one()
  }

  def findAll: Future[Seq[Role]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
