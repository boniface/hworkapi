package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonIdentity

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonIdentityRepository extends CassandraTable[PersonIdentityRepository,PersonIdentity]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this)
  object personIdentityId extends StringColumn(this)
  object idType extends StringColumn(this)
  object idValue extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonIdentity = {
    PersonIdentity(organisationId(r), userId(r), personIdentityId(r), idType(r), idValue(r), date(r), state(r))
  }
}

object PersonIdentityRepository extends PersonIdentityRepository with RootConnector {
  override lazy val tableName = "personidentity"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personIdentity: PersonIdentity): Future[ResultSet] = {
    insert
      .value(_.organisationId, personIdentity.organisationId)
      .value(_.userId, personIdentity.userId)
      .value(_.personIdentityId, personIdentity.personIdentityId)
      .value(_.idType, personIdentity.idType)
      .value(_.idValue, personIdentity.idValue)
      .value(_.date, personIdentity.date)
      .value(_.state, personIdentity.state)
      .future()
  }

  def findById(organisationId: String):Future[Option[PersonIdentity]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[PersonIdentity]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
