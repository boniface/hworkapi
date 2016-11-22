package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonEducationHistory

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonEducationHistoryRepository extends CassandraTable[PersonEducationHistoryRepository,PersonEducationHistory]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personEducationHistoryId extends StringColumn(this) with PrimaryKey[String]
  object institutionName extends StringColumn(this)
  object institutionLocation extends StringColumn(this)
  object yearOfGraduation extends IntColumn(this)
  object educationTypeId extends StringColumn(this)
  object degreeId extends StringColumn(this)
  object notes extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonEducationHistory = {
    PersonEducationHistory(organisationId(r), userId(r), personEducationHistoryId(r), institutionName(r),
      institutionLocation(r), yearOfGraduation(r), educationTypeId(r), degreeId(r), notes(r), date(r), state(r))
  }
}

object PersonEducationHistoryRepository extends PersonEducationHistoryRepository with RootConnector {
  override lazy val tableName = "personeducationhistory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personEducationHistory: PersonEducationHistory): Future[ResultSet] = {
    insert
      .value(_.organisationId, personEducationHistory.organisationId)
      .value(_.userId, personEducationHistory.userId)
      .value(_.personEducationHistoryId, personEducationHistory.personEducationHistoryId)
      .value(_.institutionName, personEducationHistory.institutionName)
      .value(_.institutionLocation, personEducationHistory.institutionLocation)
      .value(_.yearOfGraduation, personEducationHistory.yearOfGraduation)
      .value(_.educationTypeId, personEducationHistory.educationTypeId)
      .value(_.degreeId, personEducationHistory.degreeId)
      .value(_.notes, personEducationHistory.notes)
      .value(_.date, personEducationHistory.date)
      .value(_.state, personEducationHistory.state)

      .future()
  }

  def findById(organisationId: String, userId: String, personEducationHistoryId: String):Future[Option[PersonEducationHistory]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personEducationHistoryId eqs personEducationHistoryId).one()
  }
  def findAll: Future[Seq[PersonEducationHistory]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonEducationHistory(organisationId: String): Future[Seq[PersonEducationHistory]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personEducationHistoryId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personEducationHistoryId eqs personEducationHistoryId).future()
  }
}
