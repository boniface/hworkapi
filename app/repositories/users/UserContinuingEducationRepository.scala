package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserContinuingEducation

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserContinuingEducationRepository extends CassandraTable[UserContinuingEducationRepository,UserContinuingEducation]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personContinuingEducationId extends StringColumn(this) with PrimaryKey[String]
  object courseId extends StringColumn(this)
  object competencyEvaluationId extends StringColumn(this)
  object courseScheduleId extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): UserContinuingEducation = {
    UserContinuingEducation(organisationId(r), userId(r), personContinuingEducationId(r), courseId(r),
      competencyEvaluationId(r), courseScheduleId(r), date(r), state(r))
  }
}

object UserContinuingEducationRepository extends UserContinuingEducationRepository with RootConnector {
  override lazy val tableName = "personcontinuingeducation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personContinuingEducation: UserContinuingEducation): Future[ResultSet] = {
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

  def findById(organisationId: String, userId: String, personContinuingEducationId: String):Future[Option[UserContinuingEducation]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personContinuingEducationId eqs personContinuingEducationId).one()
  }
  def findAll: Future[Seq[UserContinuingEducation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonContinuingEducation(organisationId: String): Future[Seq[UserContinuingEducation]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personContinuingEducationId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personContinuingEducationId eqs personContinuingEducationId).future()
  }
}
