package repositories.training.institutions
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.institutions.TrainingInstructor

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstructorRepository  extends CassandraTable[TrainingInstructorRepository,TrainingInstructor]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object TrainingInstructorId extends StringColumn(this) with PrimaryKey[String]
  object title extends StringColumn(this)
  object firstName extends StringColumn(this)
  object lastName extends StringColumn(this)
  object qualification extends StringColumn(this)
  object areasOfExpertise extends StringColumn(this)


  override def fromRow(r: Row): TrainingInstructor = {
    TrainingInstructor(organisationId(r), TrainingInstructorId(r),title(r), firstName(r),lastName(r), qualification(r), areasOfExpertise(r))
  }
}

object TrainingInstructorRepository extends TrainingInstructorRepository with RootConnector {
  override lazy val tableName = "traininginstructor"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(trainingInstructor: TrainingInstructor): Future[ResultSet] = {
    insert
      .value(_.organisationId, trainingInstructor.organisationId)
      .value(_.TrainingInstructorId, trainingInstructor.TrainingInstructorId)
      .value(_.title, trainingInstructor.title)
      .value(_.firstName, trainingInstructor.firstName)
      .value(_.lastName, trainingInstructor.lastName)
      .value(_.qualification, trainingInstructor.qualification)
      .value(_.areasOfExpertise, trainingInstructor.areasOfExpertise)
      .future()
  }

  def findById(organisationId: String, TrainingInstructorId: String):Future[Option[TrainingInstructor]] = {
    select.where(_.organisationId eqs organisationId). and(_.TrainingInstructorId eqs TrainingInstructorId).one()
  }
  def findAll: Future[Seq[TrainingInstructor]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTrainingInstructor(organisationId: String): Future[Seq[TrainingInstructor]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, TrainingInstructorId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.TrainingInstructorId eqs TrainingInstructorId).future()
  }
}
