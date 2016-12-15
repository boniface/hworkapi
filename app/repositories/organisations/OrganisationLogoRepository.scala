package repositories.organisations
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationLogo

import scala.concurrent.Future

/**
  * Created by Isiphile on 2016-10-22.
  */
class OrganisationLogoRepository extends CassandraTable[OrganisationLogoRepository,OrganisationLogo]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationLogoId extends StringColumn(this) with PrimaryKey[String]
  object url extends StringColumn(this)
  object size extends OptionalStringColumn(this)
  object description extends StringColumn(this)
  object mime extends StringColumn(this)
  object date extends DateTimeColumn(this)


  override def fromRow(r: Row): OrganisationLogo = {
    OrganisationLogo(organisationId(r), organisationLogoId(r), url(r),
      size(r),description(r), mime(r), date(r))
  }
}

object OrganisationLogoRepository extends OrganisationLogoRepository with RootConnector {
  override lazy val tableName = "organisationlogo"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationLogo :OrganisationLogo): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationLogo.organisationId)
      .value(_.organisationLogoId, organisationLogo.organisationLogoId)
      .value(_.url, organisationLogo.url)
      .value(_.size, organisationLogo.size)
      .value(_.description, organisationLogo.description)
      .value(_.mime, organisationLogo.mime)
      .value(_.date, organisationLogo.date)
      .future()
  }

  def getFileResultById(organisationId: String, organisationLogoId: String): Future[Option[OrganisationLogo]] = {
    select.where(_.organisationId eqs organisationId).and (_.organisationLogoId eqs organisationLogoId).one()
  }

  def findAll: Future[Seq[OrganisationLogo]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationLogo(organisationId: String): Future[Seq[OrganisationLogo]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, organisationLogoId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.organisationLogoId eqs organisationLogoId).future()
  }
}
