package repositories.payroll.common
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.common.PensionFundPlan

import scala.concurrent.Future

/**
  * Created by luxolom on 2016/11/23.
  */
class PensionFundPlanRepository  extends CassandraTable[PensionFundPlanRepository,PensionFundPlan]{

  object pensionFundId extends StringColumn(this) with PrimaryKey[String]
  object date extends DateTimeColumn(this)
  object details extends MapColumn[String, String](this)


  override def fromRow(r: Row): PensionFundPlan = {
    PensionFundPlan(pensionFundId(r), date(r),details(r))
  }
}

object PensionFundPlanRepository extends PensionFundPlanRepository with RootConnector {
  override lazy val tableName = "pensionfundplan"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(pensionFundPlan: PensionFundPlan): Future[ResultSet] = {
    insert
      .value(_.pensionFundId, pensionFundPlan.pensionFundId)
      .value(_.date, pensionFundPlan.date)
      .value(_.details, pensionFundPlan.details)
      .future()
  }

  def getPensionFundPlanById(pensionFundId: String): Future[Option[PensionFundPlan]] = {
    select.where(_.pensionFundId eqs pensionFundId).one()
  }

  def findAll: Future[Seq[PensionFundPlan]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPensionFundPlans(pensionFundId: String): Future[Seq[PensionFundPlan]] = {
    select.where(_.pensionFundId eqs pensionFundId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(pensionFundId: String): Future[ResultSet] = {
    delete.where(_.pensionFundId eqs pensionFundId).future()
  }
}
