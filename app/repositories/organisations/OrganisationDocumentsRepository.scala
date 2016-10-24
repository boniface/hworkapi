package repositories.organisations

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationDocuments

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationDocumentsRepository extends CassandraTable[OrganisationDocumentsRepository,OrganisationDocuments]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationDocumentsId extends StringColumn(this)  with PrimaryKey[String]
  object description extends StringColumn(this)
  object url extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object permission extends SetColumn[OrganisationDocumentsRepository,OrganisationDocuments, String](this)
  object state extends StringColumn(this)


  override def fromRow(r: Row): OrganisationDocuments = {
    OrganisationDocuments(organisationId(r), organisationDocumentsId(r), description(r), url(r),date(r),
      permission(r), state(r))
  }
}

object OrganisationDocumentsRepository extends OrganisationDocumentsRepository with RootConnector {
  override lazy val tableName = "organisationdocuments"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationDocuments :OrganisationDocuments): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationDocuments.organisationId)
      .value(_.organisationDocumentsId, organisationDocuments.organisationDocumentsId)
      .value(_.description, organisationDocuments.description)
      .value(_.url, organisationDocuments.url)
      .value(_.date, organisationDocuments.date)
      .value(_.permission, organisationDocuments.permission)
      .value(_.state, organisationDocuments.state)
      .future()
  }

  def getFileResultById(organisationId: String, organisationDocumentsId: String): Future[Option[OrganisationDocuments]] = {
    select.where(_.organisationId eqs organisationId).and (_.organisationDocumentsId eqs organisationDocumentsId).one()
  }

  def findAll: Future[Seq[OrganisationDocuments]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationDocuments(organisationId: String): Future[Seq[OrganisationDocuments]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, organisationDocumentsId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.organisationDocumentsId eqs organisationDocumentsId).future()
  }
}
