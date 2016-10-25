package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonPosition

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonPositionRepository extends CassandraTable[PersonPositionRepository,PersonPosition]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this)
  object personPositionId extends StringColumn(this)
  object statusDate extends DateTimeColumn(this)
  object positionId extends StringColumn(this)
  object statusId extends StringColumn(this)
  object reason extends StringColumn(this)

  override def fromRow(r: Row): PersonPosition = {
    PersonPosition(organisationId(r), userId(r), personPositionId(r), statusDate(r), positionId(r), statusId(r), reason(r))
  }
}

object PersonPositionRepository extends PersonPositionRepository with RootConnector {
  override lazy val tableName = "personposition"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personPosition: PersonPosition): Future[ResultSet] = {
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

  def findById(organisationId: String):Future[Option[PersonPosition]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[PersonPosition]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
