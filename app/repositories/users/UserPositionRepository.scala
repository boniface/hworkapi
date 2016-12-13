package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserPosition

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserPositionRepository extends CassandraTable[UserPositionRepository,UserPosition]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personPositionId extends StringColumn(this) with PrimaryKey[String]
  object statusDate extends DateTimeColumn(this)
  object positionId extends StringColumn(this)
  object statusId extends StringColumn(this)
  object reason extends StringColumn(this)

  override def fromRow(r: Row): UserPosition = {
    UserPosition(organisationId(r), userId(r), personPositionId(r), statusDate(r), positionId(r), statusId(r), reason(r))
  }
}

object UserPositionRepository extends UserPositionRepository with RootConnector {
  override lazy val tableName = "personposition"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personPosition: UserPosition): Future[ResultSet] = {
    insert
      .value(_.organisationId, personPosition.organisationId)
      .value(_.userId, personPosition.userId)
      .value(_.personPositionId, personPosition.personPositionId)
      .value(_.statusDate, personPosition.statusDate)
      .value(_.positionId, personPosition.positionId)
      .value(_.statusId, personPosition.statusId)
      .value(_.reason, personPosition.reason)
      .future()
  }

  def findById(organisationId: String, userId: String, personPositionId: String):Future[Option[UserPosition]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personPositionId eqs personPositionId).one()
  }
  def findAll: Future[Seq[UserPosition]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonPosition(organisationId: String): Future[Seq[UserPosition]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personPositionId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personPositionId eqs personPositionId).future()
  }
}
