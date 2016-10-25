package repositories.organisations
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.Organisation

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationRepository extends CassandraTable[OrganisationRepository,Organisation]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object details extends MapColumn[OrganisationRepository,Organisation, String, String](this)
  object adminattached extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)


  override def fromRow(r: Row): Organisation = {
    Organisation(organisationId(r), name(r), details(r),adminattached(r),date(r), state(r))
  }
}

object OrganisationRepository extends OrganisationRepository with RootConnector {
  override lazy val tableName = "organisation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisation :Organisation): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisation.organisationId)
      .value(_.name, organisation.name)
      .value(_.details, organisation.details)
      .value(_.adminattached, organisation.adminattached)
      .value(_.date, organisation.date)
      .value(_.state, organisation.state)
      .future()
  }

  def findById(organisationId: String):Future[Option[Organisation]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[Organisation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
