package repositories.training.institutions
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitutionLocation

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionLocationRepository extends CassandraTable[TrainingInstitutionLocationRepository,TrainingInstitutionLocation]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object TrainingInstitutionLocationId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object locationTypeId extends StringColumn(this) with PrimaryKey[String]
  object code extends StringColumn(this)
  object latitude extends StringColumn(this)
  object longitude extends StringColumn(this)
  object state extends StringColumn(this)
  object date extends DateTimeColumn(this)


  override def fromRow(r: Row): TrainingInstitutionLocation = {
    TrainingInstitutionLocation(organisationId(r), TrainingInstitutionLocationId(r),name(r),
      locationTypeId(r),code(r), latitude(r), longitude(r), state(r), date(r))
  }
}

object TrainingInstitutionLocationRepository extends TrainingInstitutionLocationRepository with RootConnector {
  override lazy val tableName = "traininginstitutionlocation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(trainingInstitutionLocation: TrainingInstitutionLocation): Future[ResultSet] = {
    insert
      .value(_.organisationId, trainingInstitutionLocation.organisationId)
      .value(_.TrainingInstitutionLocationId, trainingInstitutionLocation.TrainingInstitutionLocationId)
      .value(_.name, trainingInstitutionLocation.name)
      .value(_.locationTypeId, trainingInstitutionLocation.locationTypeId)
      .value(_.code, trainingInstitutionLocation.code)
      .value(_.latitude, trainingInstitutionLocation.latitude)
      .value(_.longitude, trainingInstitutionLocation.longitude)
      .value(_.state, trainingInstitutionLocation.state)
      .value(_.date, trainingInstitutionLocation.date)
      .future()
  }

  def findById(organisationId: String, TrainingInstitutionLocationId: String, locationTypeId: String):Future[Option[TrainingInstitutionLocation]] = {
    select.where(_.organisationId eqs organisationId). and(_.TrainingInstitutionLocationId eqs TrainingInstitutionLocationId). and (_.locationTypeId eqs locationTypeId).one()
  }
  def findAll: Future[Seq[TrainingInstitutionLocation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTrainingInstitutionLocation(organisationId: String): Future[Seq[TrainingInstitutionLocation]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, TrainingInstitutionLocationId: String, locationTypeId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.TrainingInstitutionLocationId eqs TrainingInstitutionLocationId). and (_.locationTypeId eqs locationTypeId).future()
  }
}
