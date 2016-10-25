package repositories.organisations
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationLocation

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationLocationRepository extends CassandraTable[OrganisationLocationRepository,OrganisationLocation]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationLocationId extends StringColumn(this)
  object name extends StringColumn(this)
  object locationTypeId extends StringColumn(this)
  object code extends StringColumn(this)
  object latitude extends StringColumn(this)
  object longitude extends StringColumn(this)
  object parentId extends StringColumn(this)
  object state extends StringColumn(this)
  object date extends DateTimeColumn(this)


  override def fromRow(r: Row): OrganisationLocation = {
    OrganisationLocation(organisationId(r), organisationLocationId(r), name(r),
      locationTypeId(r),code(r), latitude(r), longitude(r), parentId(r), state(r), date(r))
  }
}

object OrganisationLocationRepository extends OrganisationLocationRepository with RootConnector {
  override lazy val tableName = "organisationlocation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationLocation :OrganisationLocation): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationLocation.organisationId)
      .value(_.organisationLocationId, organisationLocation.organisationLocationId)
      .value(_.name, organisationLocation.name)
      .value(_.locationTypeId, organisationLocation.locationTypeId)
      .value(_.code, organisationLocation.code)
      .value(_.latitude, organisationLocation.latitude)
      .value(_.longitude, organisationLocation.longitude)
      .value(_.parentId, organisationLocation.parentId)
      .value(_.state, organisationLocation.state)
      .value(_.date, organisationLocation.date)
      .future()
  }

  def findById(organisationId: String):Future[Option[OrganisationLocation]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[OrganisationLocation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
