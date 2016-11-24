package repositories.payroll.funding

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.funding.FundingSources
import scala.concurrent.Future
/**
  * Created by hashcode on 2016/01/09.
  */
//Start of Louise
class FunderRepository extends CassandraTable[FunderRepository,FundingSources]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object fundingSourcesId extends StringColumn(this)  with PrimaryKey[String]
  object name extends StringColumn(this)
  object costCenterNumber extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object details extends MapColumn[String, String](this)

  override def fromRow(r: Row): FundingSources = {
    FundingSources(organisationId(r), fundingSourcesId(r), name(r), costCenterNumber(r), date(r), details(r))
  }
}
object FunderRepository extends FunderRepository with RootConnector {
  override lazy val tableName = "funder"

  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def save(fundingSources: FundingSources): Future[ResultSet] = {
    insert
      .value(_.organisationId, fundingSources.organisationId)
      .value(_.fundingSourcesId, fundingSources.fundingSourcesId)
      .value(_.name, fundingSources.name)
      .value(_.costCenterNumber, fundingSources.costCenterNumber)
      .value(_.date, fundingSources.date)
      .value(_.details, fundingSources.details)
      .future()
  }

  def getFileResultById(organisationId: String, fundingSourcesId: String): Future[Option[FundingSources]] = {
    select.where(_.organisationId eqs organisationId).and (_.fundingSourcesId eqs fundingSourcesId).one()
  }

  def findAll: Future[Seq[FundingSources]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getFunder(organisationId: String): Future[Seq[FundingSources]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, fundingSourcesId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.fundingSourcesId eqs fundingSourcesId).future()
  }
}
//End of Louise