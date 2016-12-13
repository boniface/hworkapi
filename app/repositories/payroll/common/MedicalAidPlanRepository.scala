package repositories.payroll.common
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.common.MedicalAidPlan

import scala.concurrent.Future
/**
  * Created by luxolom on 2016/11/23.
  */
class MedicalAidPlanRepository  extends CassandraTable[MedicalAidPlanRepository,MedicalAidPlan]{

  object medicalAidId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object details extends MapColumn[String, String](this)


  override def fromRow(r: Row): MedicalAidPlan = {
    MedicalAidPlan(medicalAidId(r), name(r),details(r))
  }
}

object MedicalAidPlanRepository extends MedicalAidPlanRepository with RootConnector {
  override lazy val tableName = "medicalaidplan"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(medicalAidPlan: MedicalAidPlan): Future[ResultSet] = {
    insert
      .value(_.medicalAidId, medicalAidPlan.medicalAidId)
      .value(_.name, medicalAidPlan.name)
      .value(_.details, medicalAidPlan.details)
      .future()
  }

  def getMedicalAidPlanById(medicalAidId: String): Future[Option[MedicalAidPlan]] = {
    select.where(_.medicalAidId eqs medicalAidId).one()
  }

  def findAll: Future[Seq[MedicalAidPlan]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getMedicalAidPlans(medicalAidId: String): Future[Seq[MedicalAidPlan]] = {
    select.where(_.medicalAidId eqs medicalAidId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(medicalAidId: String): Future[ResultSet] = {
    delete.where(_.medicalAidId eqs medicalAidId).future()
  }
}
