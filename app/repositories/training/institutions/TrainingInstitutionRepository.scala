package repositories.training.institutions
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitution

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionRepository  extends CassandraTable[TrainingInstitutionRepository,TrainingInstitution]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object trainingInstitutionId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object email extends StringColumn(this)
  object status extends StringColumn(this)


  override def fromRow(r: Row): TrainingInstitution = {
    TrainingInstitution(organisationId(r), trainingInstitutionId(r),name(r), email(r),status(r))
  }
}

object TrainingInstitutionRepository extends TrainingInstitutionRepository with RootConnector {
  override lazy val tableName = "traininginstitution"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(trainingInstitution: TrainingInstitution): Future[ResultSet] = {
    insert
      .value(_.organisationId, trainingInstitution.organisationId)
      .value(_.trainingInstitutionId, trainingInstitution.trainingInstitutionId)
      .value(_.name, trainingInstitution.name)
      .value(_.email, trainingInstitution.email)
      .value(_.status, trainingInstitution.status)
      .future()
  }

  def findById(organisationId: String, trainingInstitutionId: String):Future[Option[TrainingInstitution]] = {
    select.where(_.organisationId eqs organisationId). and(_.trainingInstitutionId eqs trainingInstitutionId).one()
  }
  def findAll: Future[Seq[TrainingInstitution]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTrainingInstitution(organisationId: String): Future[Seq[TrainingInstitution]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, trainingInstitutionId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.trainingInstitutionId eqs trainingInstitutionId).future()
  }
}
