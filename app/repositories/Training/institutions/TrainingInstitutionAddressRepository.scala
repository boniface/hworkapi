package repositories.training.institutions
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitutionAddress

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionAddressRepository extends CassandraTable[TrainingInstitutionAddressRepository,TrainingInstitutionAddress]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object TrainingInstitutionAddressId extends StringColumn(this) with PrimaryKey[String]
  object trainingInstitutionLocationId extends StringColumn(this) with PrimaryKey[String]
  object addressTypeId extends StringColumn(this)
  object details extends MapColumn[TrainingInstitutionAddressRepository,TrainingInstitutionAddress, String, String](this)

  override def fromRow(r: Row): TrainingInstitutionAddress = {
    TrainingInstitutionAddress(organisationId(r),TrainingInstitutionAddressId(r),trainingInstitutionLocationId(r), addressTypeId(r), details(r))
  }
}

object TrainingInstitutionAddressRepository extends TrainingInstitutionAddressRepository with RootConnector {
  override lazy val tableName = "traininginstitutionaddress"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(trainingInstitutionAddress: TrainingInstitutionAddress): Future[ResultSet] = {
    insert
      .value(_.organisationId, trainingInstitutionAddress.organisationId)
      .value(_.TrainingInstitutionAddressId, trainingInstitutionAddress.TrainingInstitutionAddressId)
      .value(_.trainingInstitutionLocationId, trainingInstitutionAddress.trainingInstitutionLocationId)
      .value(_.addressTypeId, trainingInstitutionAddress.addressTypeId)
      .value(_.details, trainingInstitutionAddress.details)
      .future()
  }

  def findById(organisationId: String, TrainingInstitutionAddressId: String, trainingInstitutionLocationId: String):Future[Option[TrainingInstitutionAddress]] = {
    select.where(_.organisationId eqs organisationId). and(_.TrainingInstitutionAddressId eqs TrainingInstitutionAddressId). and (_.trainingInstitutionLocationId eqs trainingInstitutionLocationId).one()
  }
  def findAll: Future[Seq[TrainingInstitutionAddress]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTrainingInstitutionAddress(organisationId: String): Future[Seq[TrainingInstitutionAddress]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, TrainingInstitutionAddressId: String, trainingInstitutionLocationId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.TrainingInstitutionAddressId eqs TrainingInstitutionAddressId). and (_.trainingInstitutionLocationId eqs trainingInstitutionLocationId).future()
  }
}
