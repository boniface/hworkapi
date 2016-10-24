package repositories.payroll.common
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.common.LeaveType

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-18.
  */
class LeaveTypeRepository extends CassandraTable[LeaveTypeRepository,LeaveType]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object leaveTypeId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)


  override def fromRow(r: Row): LeaveType = {
    LeaveType(organisationId(r), leaveTypeId(r), name(r))
  }
}

object LeaveTypeRepository extends LeaveTypeRepository with RootConnector {
  override lazy val tableName = "leavetype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(leaveType :LeaveType): Future[ResultSet] = {
    insert
      .value(_.organisationId, leaveType.organisationId)
      .value(_.leaveTypeId, leaveType.leaveTypeId)
      .value(_.name, leaveType.name)
      .future()
  }

  def getFileResultById(organisationId: String, leaveTypeId: String): Future[Option[LeaveType]] = {
    select.where(_.organisationId eqs organisationId).and (_.leaveTypeId eqs leaveTypeId).one()
  }

  def findAll: Future[Seq[LeaveType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getLeaveType(organisationId: String): Future[Seq[LeaveType]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, leaveTypeId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.leaveTypeId eqs leaveTypeId).future()
  }
}
