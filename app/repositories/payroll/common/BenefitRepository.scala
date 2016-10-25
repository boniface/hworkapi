package repositories.payroll.common


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.common.Benefit
import scala.concurrent.Future
/**
 * Created by hashcode on 2016/01/09.
 */
class BenefitRepository extends CassandraTable[BenefitRepository,Benefit]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object benefitId extends StringColumn(this)
  object name extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)


  override def fromRow(r: Row): Benefit = {
    Benefit(organisationId(r), benefitId(r), name(r), date(r), state(r))
  }
}

object BenefitRepository extends BenefitRepository with RootConnector {
  override lazy val tableName = "benefit"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(benefit: Benefit): Future[ResultSet] = {
    insert
      .value(_.organisationId, benefit.organisationId)
      .value(_.benefitId, benefit.benefitId)
      .value(_.name, benefit.name)
      .value(_.date, benefit.date)
      .value(_.state, benefit.state)
      .future()
  }

  def findById(organisationId: String):Future[Option[Benefit]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[Benefit]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
