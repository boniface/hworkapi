package repositories.payroll.common

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.common.{Benefit, PensionFund}

import scala.concurrent.Future
/**
  * Created by luxolom on 2016/11/23.
  */

class PensionFundRepository extends CassandraTable[PensionFundRepository,PensionFund]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object pensionFundId extends StringColumn(this)  with PrimaryKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): PensionFund = {
    PensionFund(organisationId(r), pensionFundId(r), name(r))
  }
}

object PensionFundRepository extends PensionFundRepository with RootConnector {
  override lazy val tableName = "pensionfund"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(pensionFund: PensionFund): Future[ResultSet] = {
    insert
      .value(_.organisationId, pensionFund.organisationId)
      .value(_.pensionFundId, pensionFund.pensionFundId)
      .value(_.name, pensionFund.name)
      .future()
  }

  def getFileResultById(organisationId: String, benefitId: String): Future[Option[PensionFund]] = {
    select.where(_.organisationId eqs organisationId).and (_.pensionFundId eqs benefitId).one()
  }

  def findAll: Future[Seq[PensionFund]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPensionFund(organisationId: String): Future[Seq[PensionFund]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, pensionFundId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.pensionFundId eqs pensionFundId).future()
  }
}

