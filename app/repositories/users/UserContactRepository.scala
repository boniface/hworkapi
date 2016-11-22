package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserContact

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserContactRepository extends CassandraTable[UserContactRepository,UserContact]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this)with PrimaryKey[String]
  object userContactId extends StringColumn(this) with PrimaryKey[String]
  object contactTypeId extends StringColumn(this)
  object details extends MapColumn[UserAddressRepository,UserContact, String, String](this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): UserContact = {
    UserContact(organisationId(r), userId(r), userContactId(r), contactTypeId(r), details(r), date(r), state(r))
  }
}

object UserContactRepository extends UserContactRepository with RootConnector {
  override lazy val tableName = "usercontact"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userContact: UserContact): Future[ResultSet] = {
    insert
      .value(_.organisationId, userContact.organisationId)
      .value(_.userId, userContact.userId)
      .value(_.userContactId, userContact.userContactId)
      .value(_.contactTypeId, userContact.contactTypeId)
      .value(_.details, userContact.details)
      .value(_.date, userContact.date)
      .value(_.state, userContact.state)
      .future()
  }

  def findById(organisationId: String, userId: String, userContactId: String):Future[Option[UserContact]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.userContactId eqs userContactId).one()
  }
  def findAll: Future[Seq[UserContact]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getUserContact(organisationId: String): Future[Seq[UserContact]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, userContactId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.userContactId eqs userContactId).future()
  }
}
