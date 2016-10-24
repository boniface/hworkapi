package repositories.common.address
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.address.OfficeType

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-18.
  */
class OfficeTypeRepository extends CassandraTable[OfficeTypeRepository,OfficeType]{
  object officeTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object code extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): OfficeType = {
    OfficeType(officeTypeId(r),name(r), code(r), state(r))
  }
}

object OfficeTypeRepository extends OfficeTypeRepository with RootConnector {
  override lazy val tableName = "contypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(contypes: OfficeType): Future[ResultSet] = {
    insert
      .value(_.officeTypeId, contypes.officeTypeId)
      .value(_.name, contypes.name)
      .value(_.code, contypes.code)
      .value(_.state, contypes.state)
      .future()
  }

  def findById(officeTypeId: String):Future[Option[OfficeType]] = {
    select.where(_.officeTypeId eqs officeTypeId).one()
  }
  def findAll: Future[Seq[OfficeType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getOfficeType(officeTypeId: String): Future[Seq[OfficeType]] = {
    select.where(_.officeTypeId eqs officeTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(officeTypeId:String): Future[ResultSet] = {
    delete.where(_.officeTypeId eqs officeTypeId).future()
  }
}
