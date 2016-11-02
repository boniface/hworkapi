package repositories.common.education

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.education.EducationType

import scala.concurrent.Future

sealed class EducationTypeRepository extends CassandraTable[EducationTypeRepository,EducationType]{
  object educationTypeId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): EducationType = {
    EducationType(educationTypeId(r),name(r))
  }
}

object EducationTypeRepository extends EducationTypeRepository with RootConnector {
  override lazy val tableName = "edutype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(edutype: EducationType): Future[ResultSet] = {
    insert
      .value(_.educationTypeId, edutype.educationTypeId)
      .value(_.name, edutype.name)
      .future()
  }

  def getEducationTypeById(educationTypeId: String):Future[Option[EducationType]] = {
    select.where(_.educationTypeId eqs educationTypeId).one()
  }
  def getEducationTypes(educationTypeId:String): Future[Seq[EducationType]] = {
    select.where(_.educationTypeId eqs educationTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(educationTypeId:String): Future[ResultSet] = {
    delete.where(_.educationTypeId eqs educationTypeId).future()
  }
}
