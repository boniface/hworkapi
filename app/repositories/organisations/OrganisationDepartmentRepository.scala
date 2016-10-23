package repositories.organisations
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisations.OrganisationDepartment

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-22.
  */
class OrganisationDepartmentRepository extends CassandraTable[OrganisationDepartmentRepository,OrganisationDepartment]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object organisationDepartmentId extends StringColumn(this)
  object name extends StringColumn(this)
  object description extends StringColumn(this)
  object active extends StringColumn(this)
  object state extends StringColumn(this)
  object date extends DateTimeColumn(this)


  override def fromRow(r: Row): OrganisationDepartment = {
    OrganisationDepartment(organisationId(r), organisationDepartmentId(r),name(r),
      description(r), active(r), state(r), date(r))
  }
}

object OrganisationDepartmentRepository extends OrganisationDepartmentRepository with RootConnector {
  override lazy val tableName = "organisationdepartment"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationDepartment :OrganisationDepartment): Future[ResultSet] = {
    insert
      .value(_.organisationId, organisationDepartment.organisationId)
      .value(_.organisationDepartmentId, organisationDepartment.organisationDepartmentId)
      .value(_.name, organisationDepartment.name)
      .value(_.description, organisationDepartment.description)
      .value(_.active, organisationDepartment.active)
      .value(_.state, organisationDepartment.state)
      .value(_.date, organisationDepartment.date)
      .future()
  }

  def findById(organisationId: String):Future[Option[OrganisationDepartment]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[OrganisationDepartment]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
