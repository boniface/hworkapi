package repositories.organisations
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationContact

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationContactRepository extends CassandraTable[OrganisationContactRepository,OrganisationContact]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationEmail extends StringColumn(this)
  object organisationContactId extends StringColumn(this) with PrimaryKey[String]
  object contactTypeId extends StringColumn(this)
  object details extends MapColumn[OrganisationRepository,OrganisationContact, String, String](this)


  override def fromRow(r: Row): OrganisationContact = {
    OrganisationContact(organisationId(r), organisationEmail(r),organisationContactId(r),
      contactTypeId(r), details(r))
  }
}

object OrganisationContactRepository extends OrganisationContactRepository with RootConnector {
  override lazy val tableName = "organisationcontact"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationContact :OrganisationContact): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationContact.organisationId)
      .value(_.organisationEmail, organisationContact.organisationEmail)
      .value(_.organisationContactId, organisationContact.organisationContactId)
      .value(_.contactTypeId, organisationContact.contactTypeId)
      .value(_.details, organisationContact.details)
      .future()
  }

  def getFileResultById(organisationId: String, organisationContactId: String): Future[Option[OrganisationContact]] = {
    select.where(_.organisationId eqs organisationId).and (_.organisationContactId eqs organisationContactId).one()
  }

  def findAll: Future[Seq[OrganisationContact]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationContact(organisationId: String): Future[Seq[OrganisationContact]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, organisationContactId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.organisationContactId eqs organisationContactId).future()
  }
}
