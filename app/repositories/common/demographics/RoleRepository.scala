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

  object roleId extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(r: Row): Role = {
    Role(roleId(r), name(r), description(r))
  }
}

object RoleRepository extends RoleRepository with RootConnector {
  override lazy val tableName = "roles"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(role: Role): Future[ResultSet] = {
    insert
      .value(_.roleId, role.roleId)
      .value(_.name, role.name)
      .value(_.description, role.description)
      .future()
  }

  def getRoleById(roleId: String): Future[Option[Role]] = {
    select.where(_.roleId eqs roleId).one()
  }

  def getRoles(roleId:String): Future[Seq[Role]] = {
    select.where(_.roleId eqs roleId).fetchEnumerator() run Iteratee.collect()
  }
  def getRole(roleId: String): Future[Seq[Role]] = {
    select.where(_.roleId eqs roleId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(roleId: String): Future[ResultSet] = {
    delete.where(_.roleId eqs roleId).future()
  }
}
