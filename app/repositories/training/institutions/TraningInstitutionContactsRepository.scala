package repositories.training.institutions
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.institutions.TraningInstitutionContacts

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class TraningInstitutionContactsRepository extends CassandraTable[TraningInstitutionContactsRepository,TraningInstitutionContacts]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object traningInstitutionContactsId extends StringColumn(this) with PrimaryKey[String]
  object contactTypeId extends StringColumn(this)
  object details extends MapColumn[ String, String](this)

  override def fromRow(r: Row): TraningInstitutionContacts = {
    TraningInstitutionContacts(organisationId(r),traningInstitutionContactsId(r),contactTypeId(r), details(r))
  }
}

object TraningInstitutionContactsRepository extends TraningInstitutionContactsRepository with RootConnector {
  override lazy val tableName = "traninginstitutioncontacts"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(traningInstitutionContacts: TraningInstitutionContacts): Future[ResultSet] = {
    insert
      .value(_.organisationId, traningInstitutionContacts.organisationId)
      .value(_.traningInstitutionContactsId, traningInstitutionContacts.traningInstitutionContactsId)
      .value(_.contactTypeId, traningInstitutionContacts.contactTypeId)
      .value(_.details, traningInstitutionContacts.details)
      .future()
  }

  def findById(organisationId: String, traningInstitutionContactsId: String):Future[Option[TraningInstitutionContacts]] = {
    select.where(_.organisationId eqs organisationId). and(_.traningInstitutionContactsId eqs traningInstitutionContactsId).one()
  }
  def findAll: Future[Seq[TraningInstitutionContacts]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getTraningInstitutionContacts(organisationId: String): Future[Seq[TraningInstitutionContacts]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, traningInstitutionContactsId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.traningInstitutionContactsId eqs traningInstitutionContactsId).future()
  }
}
