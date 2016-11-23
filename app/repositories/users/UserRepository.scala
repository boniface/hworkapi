package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.User

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserRepository extends CassandraTable[UserRepository,User]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object firstName extends StringColumn(this)
  object middleName extends StringColumn(this)
  object email extends StringColumn(this)
  object lastName extends StringColumn(this)
  object title extends StringColumn(this)
  object authvalue extends StringColumn(this)
  object enabled extends BooleanColumn(this)
  object accountNonExpired extends BooleanColumn(this)
  object credentialsNonExpired extends BooleanColumn(this)
  object accountNonLocked extends BooleanColumn(this)
  object state extends StringColumn(this)


  override def fromRow(r: Row): User = {
    User(organisationId(r), userId(r), firstName(r), middleName(r), email(r), lastName(r), title(r), authvalue(r),
      enabled(r), accountNonExpired(r),credentialsNonExpired(r), accountNonLocked(r), state(r))
  }
}

object UserRepository extends UserRepository with RootConnector {
  override lazy val tableName = "user"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.organisationId, user.organisationId)
      .value(_.userId, user.userId)
      .value(_.firstName, user.firstName)
      .value(_.middleName, user.middleName)
      .value(_.email, user.email)
      .value(_.lastName, user.lastName)
      .value(_.title, user.title)
      .value(_.authvalue, user.authvalue)
      .value(_.enabled, user.enabled)
      .value(_.accountNonExpired, user.accountNonExpired)
      .value(_.credentialsNonExpired, user.credentialsNonExpired)
      .value(_.accountNonLocked, user.accountNonLocked)
      .value(_.state, user.state)
      .future()
  }

  def findById(organisationId: String, userId: String):Future[Option[User]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId).one()
  }
  def getAllUser: Future[Seq[User]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getUser(organisationId: String): Future[Seq[User]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId).future()
  }
}
