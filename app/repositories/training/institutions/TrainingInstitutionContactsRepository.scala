package repositories.training.institutions
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.institutions.TrainingInstitutionContacts

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TrainingInstitutionContactsRepository extends CassandraTable[TrainingInstitutionContactsRepository,TrainingInstitutionContacts]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object trainingInstitutionContactsId extends StringColumn(this) with PrimaryKey[String]
  object contactTypeId extends StringColumn(this)
  object details extends MapColumn[ String, String](this)

  override def fromRow(r: Row): TrainingInstitutionContacts = {
    TrainingInstitutionContacts(organisationId(r),trainingInstitutionContactsId(r),contactTypeId(r), details(r))
  }
}

object TrainingInstitutionContactsRepository extends TrainingInstitutionContactsRepository with RootConnector {
  override lazy val tableName = "traininginstitutioncontacts"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(trainingInstitutionContacts: TrainingInstitutionContacts): Future[ResultSet] = {
    insert
      .value(_.organisationId, trainingInstitutionContacts.organisationId)
      .value(_.trainingInstitutionContactsId, trainingInstitutionContacts.trainingInstitutionContactsId)
      .value(_.contactTypeId, trainingInstitutionContacts.contactTypeId)
      .value(_.details, trainingInstitutionContacts.details)
      .future()
  }

  def findById(organisationId: String, trainingInstitutionContactsId: String):Future[Option[TrainingInstitutionContacts]] = {
    select.where(_.organisationId eqs organisationId). and(_.trainingInstitutionContactsId eqs trainingInstitutionContactsId).one()
  }
  def findAll: Future[Seq[TrainingInstitutionContacts]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTraningInstitutionContacts(organisationId: String): Future[Seq[TrainingInstitutionContacts]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, trainingInstitutionContactsId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.trainingInstitutionContactsId eqs trainingInstitutionContactsId).future()
  }
}
