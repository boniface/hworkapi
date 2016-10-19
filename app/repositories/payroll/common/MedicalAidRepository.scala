package repositories.payroll.common
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.payroll.common.MedicalAid

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-18.
  */
class MedicalAidRepository extends CassandraTable[MedicalAidRepository,MedicalAid]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object medicalAidId extends StringColumn(this)
  object name extends StringColumn(this)
  object code extends StringColumn(this)


  override def fromRow(r: Row): MedicalAid = {
    MedicalAid(organisationId(r), medicalAidId(r), name(r), code(r))
  }
}

object MedicalAidRepository extends MedicalAidRepository with RootConnector {
  override lazy val tableName = "medicalaid"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(medicalAid: MedicalAid): Future[ResultSet] = {
    insert
      .value(_.organisationId, medicalAid.organisationId)
      .value(_.medicalAidId, medicalAid.medicalAidId)
      .value(_.name, medicalAid.name)
      .value(_.code, medicalAid.code)
      .future()
  }

  def findById(organisationId: String):Future[Option[MedicalAid]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[MedicalAid]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
