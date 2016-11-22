package repositories.organisations

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationOffice

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationOfficeRepository extends CassandraTable[OrganisationOfficeRepository,OrganisationOffice]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationOfficeId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object description extends StringColumn(this)
  object active extends StringColumn(this)
  object officeTypeId extends StringColumn(this)
  object state extends StringColumn(this)
  object date extends DateTimeColumn(this)


  override def fromRow(r: Row): OrganisationOffice = {
    OrganisationOffice(organisationId(r), organisationOfficeId(r), name(r),
      description(r),active(r), officeTypeId(r), state(r), date(r))
  }
}

object OrganisationOfficeRepository extends OrganisationOfficeRepository with RootConnector {
  override lazy val tableName = "organisationoffice"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationOffice :OrganisationOffice): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationOffice.organisationId)
      .value(_.organisationOfficeId, organisationOffice.organisationOfficeId)
      .value(_.name, organisationOffice.name)
      .value(_.description, organisationOffice.description)
      .value(_.active, organisationOffice.active)
      .value(_.officeTypeId, organisationOffice.officeTypeId)
      .value(_.state, organisationOffice.state)
      .value(_.date, organisationOffice.date)
      .future()
  }

  def getFileResultById(organisationId: String, organisationOfficeId: String): Future[Option[OrganisationOffice]] = {
    select.where(_.organisationId eqs organisationId).and (_.organisationOfficeId eqs organisationOfficeId).one()
  }

  def findAll: Future[Seq[OrganisationOffice]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationOffice(organisationId: String): Future[Seq[OrganisationOffice]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, organisationOfficeId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.organisationOfficeId eqs organisationOfficeId).future()
  }
}
