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
  * Created by hashcode on 2016/11/26.
  */
class UserEmailRepository extends CassandraTable[UserEmailRepository, User] {

  object email extends StringColumn(this) with PartitionKey[String]

  object organisationId extends StringColumn(this) with PrimaryKey[String]

  object userId extends StringColumn(this)

  object firstName extends StringColumn(this)

  object middleName extends OptionalStringColumn(this)

  object lastName extends StringColumn(this)

  object title extends StringColumn(this)

  object authvalue extends StringColumn(this)

  object enabled extends BooleanColumn(this)

  object accountNonExpired extends BooleanColumn(this)

  object credentialsNonExpired extends BooleanColumn(this)

  object accountNonLocked extends BooleanColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): User = {
    User(organisationId(r),
      userId(r),
      firstName(r),
      middleName(r),
      email(r),
      lastName(r),
      title(r),
      authvalue(r),
      enabled(r),
      accountNonExpired(r),
      credentialsNonExpired(r),
      accountNonLocked(r),
      state(r))
  }
}

object UserEmailRepository extends UserEmailRepository with RootConnector {
  override lazy val tableName = "useremails"

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

  def getUserByEmail(email: String): Future[Seq[User]] = {
    select
      .where(_.email eqs email)
      .fetchEnumerator() run Iteratee
      .collect()
  }
}
