package repositories.position

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.position.DepartureReason

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
class DepartureReasonRepository extends CassandraTable[DepartureReasonRepository, DepartureReason] {

  //  company: String,
  //  id: String,
  //  reason: String,
  //  description: String,
  //  state: String


  object organisationId extends StringColumn(this) with PrimaryKey[String]
  object departureReasonId extends StringColumn(this) with PartitionKey[String]
  object reason extends StringColumn(this)
  object description extends StringColumn(this)
  object state extends StringColumn(this)


  override def fromRow(r: Row): DepartureReason = {
    DepartureReason(
      organisationId(r),
      departureReasonId(r),
      reason(r),
      description(r),
      state(r)
    )
  }
}

object DepartureReasonRepository extends DepartureReasonRepository with RootConnector {
  override lazy val tableName = "dreasons"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(reason: DepartureReason): Future[ResultSet] = {
    insert
      .value(_.organisationId, reason.organisationId)
      .value(_.departureReasonId, reason.departureReasonId)
      .value(_.reason, reason.description)
      .value(_.description, reason.description)
      .value(_.state, reason.state)
      .future()
  }

  def getDepartureReason(organisationId: String, departureReasonId: String): Future[Option[DepartureReason]] = {
    select.where(_.organisationId eqs organisationId).and(_.departureReasonId eqs departureReasonId).one()
  }

  def getCompanyDepatureReasons(organisationId: String): Future[Seq[DepartureReason]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

}
