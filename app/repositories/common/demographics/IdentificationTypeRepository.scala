package repositories.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.IdentificationType

import scala.concurrent.Future

sealed class IdentificationTypeRepository extends CassandraTable[IdentificationTypeRepository,IdentificationType]{
  object identificationTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object description extends StringColumn(this)
  override def fromRow(r: Row): IdentificationType = {
    IdentificationType(identificationTypeId(r),name(r),description(r))
  }
}

object IdentificationTypeRepository extends IdentificationTypeRepository with RootConnector {
  override lazy val tableName = "idtypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(idtype: IdentificationType): Future[ResultSet] = {
    insert
      .value(_.identificationTypeId, idtype.identificationTypeId)
      .value(_.name, idtype.name)
      .value(_.description,idtype.description)
      .future()
  }

  def getIdentificationTypeById(identificationTypeId: String):Future[Option[IdentificationType]] = {
    select.where(_.identificationTypeId eqs identificationTypeId).one()
  }
  def getIdentificationTypes(identificationTypeId:String): Future[Seq[IdentificationType]] = {
    select.where(_.identificationTypeId eqs identificationTypeId).fetchEnumerator() run Iteratee.collect()
  }
  def getIdentificationType(identificationTypeId: String): Future[Seq[IdentificationType]] = {
    select.where(_.identificationTypeId eqs identificationTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(identificationTypeId:String): Future[ResultSet] = {
    delete.where(_.identificationTypeId eqs identificationTypeId).future()
  }
}
