package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonRole

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonRoleRepository extends CassandraTable[PersonRoleRepository,PersonRole]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object roleId extends StringColumn(this) with PrimaryKey[String]
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonRole = {
    PersonRole(organisationId(r), userId(r), roleId(r), state(r))
  }
}

object PersonRoleRepository extends PersonRoleRepository with RootConnector {
  override lazy val tableName = "personrole"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personRole: PersonRole): Future[ResultSet] = {
    insert
      .value(_.organisationId, personRole.organisationId)
      .value(_.userId, personRole.userId)
      .value(_.roleId, personRole.roleId)
      .value(_.state, personRole.state)
      .future()
  }

  def findById(organisationId: String, userId: String, roleId: String):Future[Option[PersonRole]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId).and(_.roleId eqs roleId).one()
  }
  def getAllPersonRole: Future[Seq[PersonRole]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonRole(organisationId: String): Future[Seq[PersonRole]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, roleId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId).and(_.roleId eqs roleId).future()
  }
}
