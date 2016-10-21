package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonContinuingEducation

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonContinuingEducationRepository extends CassandraTable[PersonContinuingEducationRepository,PersonContinuingEducation]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this)
  object personContinuingEducationId extends StringColumn(this)
  object courseId extends StringColumn(this)
  object competencyEvaluationId extends StringColumn(this)
  object courseScheduleId extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonContinuingEducation = {
    PersonContinuingEducation(organisationId(r), userId(r), personContinuingEducationId(r), courseId(r),
      competencyEvaluationId(r), courseScheduleId(r), date(r), state(r))
  }
}

object PersonContinuingEducationRepository extends PersonContinuingEducationRepository with RootConnector {
  override lazy val tableName = "personcontinuingeducation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personContinuingEducation: PersonContinuingEducation): Future[ResultSet] = {
    insert
      .value(_.organisationId, personContinuingEducation.organisationId)
      .value(_.userId, personContinuingEducation.userId)
      .value(_.personContinuingEducationId, personContinuingEducation.personContinuingEducationId)
      .value(_.courseId, personContinuingEducation.courseId)
      .value(_.competencyEvaluationId, personContinuingEducation.competencyEvaluationId)
      .value(_.courseScheduleId, personContinuingEducation.courseScheduleId)
      .value(_.date, personContinuingEducation.date)
      .value(_.state, personContinuingEducation.state)

      .future()
  }

  def findById(organisationId: String):Future[Option[PersonContinuingEducation]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[PersonContinuingEducation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
