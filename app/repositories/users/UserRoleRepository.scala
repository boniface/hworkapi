package repositories.users

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{ClusteringOrder, Descending, PrimaryKey, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserRole

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-21.
  */
class UserRoleRepository extends CassandraTable[UserRoleRepository, UserRole] {

  object organisationId extends StringColumn(this) with PartitionKey[String]

  object userId extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object roleId extends StringColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): UserRole = {
    UserRole(
      organisationId(r),
      userId(r),
      roleId(r),
      state(r),
      date(r))
  }
}

object UserRoleRepository extends UserRoleRepository with RootConnector {
  override lazy val tableName = "userole"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personRole: UserRole): Future[ResultSet] = {
    insert
      .value(_.organisationId, personRole.organisationId)
      .value(_.userId, personRole.userId)
      .value(_.roleId, personRole.roleId)
      .value(_.state, personRole.state)
      .value(_.date, personRole.date)
      .future()
  }

  def getUserRoles(organisationId: String, userId: String): Future[Seq[UserRole]] = {
    select
      .where(_.organisationId eqs organisationId)
      .and(_.userId eqs userId)
      .fetchEnumerator() run Iteratee
      .collect()
  }

  def getSystemRoles: Future[Seq[UserRole]] = {
    select
      .fetchEnumerator() run Iteratee
      .collect()
  }

  def getOrganisationRoles(organisationId: String): Future[Seq[UserRole]] = {
    select
      .where(_.organisationId eqs organisationId)
      .fetchEnumerator() run Iteratee
      .collect()
  }
}
