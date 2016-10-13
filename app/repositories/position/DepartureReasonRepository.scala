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

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object reason extends StringColumn(this)

  object description extends StringColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): DepartureReason = {
    DepartureReason(
      company(r),
      id(r),
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
      .value(_.company, reason.company)
      .value(_.id, reason.id)
      .value(_.reason, reason.description)
      .value(_.description, reason.description)
      .value(_.state, reason.state)
      .future()
  }

  def getDepartureReason(company: String, id: String): Future[Option[DepartureReason]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

  def getCompanyDepatureReasons(company: String): Future[Seq[DepartureReason]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }

}
