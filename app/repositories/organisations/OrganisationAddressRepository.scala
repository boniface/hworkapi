package repositories.organisations
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationAddress

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationAddressRepository extends CassandraTable[OrganisationAddressRepository,OrganisationAddress]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationEmail extends StringColumn(this)
  object organisationAddressId extends StringColumn(this) with PrimaryKey[String]
  object organisationLocationId extends StringColumn(this)
  object addressTypeId extends StringColumn(this)
  object details extends MapColumn[OrganisationRepository,OrganisationAddress, String, String](this)


  override def fromRow(r: Row): OrganisationAddress = {
    OrganisationAddress(organisationId(r), organisationEmail(r),organisationAddressId(r),
      organisationLocationId(r), addressTypeId(r), details(r))
  }
}

object OrganisationAddressRepository extends OrganisationAddressRepository with RootConnector {
  override lazy val tableName = "organisationaddress"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationAddress :OrganisationAddress): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationAddress.organisationId)
      .value(_.organisationEmail, organisationAddress.organisationEmail)
        .value(_.organisationAddressId, organisationAddress.organisationAddressId)
      .value(_.details, organisationAddress.details)
      .value(_.organisationLocationId, organisationAddress.organisationLocationId)
      .value(_.addressTypeId, organisationAddress.addressTypeId)
      .value(_.details, organisationAddress.details)
      .future()
  }

  def getFileResultById(organisationId: String, organisationAddressId: String): Future[Option[OrganisationAddress]] = {
    select.where(_.organisationId eqs organisationId).and (_.organisationAddressId eqs organisationAddressId).one()
  }

  def findAll: Future[Seq[OrganisationAddress]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationAddress(organisationId: String): Future[Seq[OrganisationAddress]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, organisationAddressId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.organisationAddressId eqs organisationAddressId).future()
  }
}
