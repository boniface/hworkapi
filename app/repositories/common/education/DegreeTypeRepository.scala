package repositories.common.education

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.education.DegreeType

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/13.
 */
class DegreeTypeRepository extends CassandraTable[DegreeTypeRepository, DegreeType] {

  object degreeTypeId extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  override def fromRow(r: Row): DegreeType = {
    DegreeType(degreeTypeId(r), name(r))
  }
}

object DegreeTypeRepository extends DegreeTypeRepository with RootConnector {
  override lazy val tableName = "degreetypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(degreeType: DegreeType): Future[ResultSet] = {
    insert
      .value(_.degreeTypeId, degreeType.degreeTypeId)
      .value(_.name, degreeType.name)
      .future()
  }

  def getDegreeTypeById(degreeTypeId: String): Future[Option[DegreeType]] = {
    select.where(_.degreeTypeId eqs degreeTypeId).one()
  }

  def getDegreeTypes(degreeTypeId:String): Future[Seq[DegreeType]] = {
    select.where(_.degreeTypeId eqs degreeTypeId).fetchEnumerator() run Iteratee.collect()
  }
  def getDegreeType(degreeTypeId: String): Future[Seq[DegreeType]] = {
    select.where(_.degreeTypeId eqs degreeTypeId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(degreeTypeId: String): Future[ResultSet] = {
    delete.where(_.degreeTypeId eqs degreeTypeId).future()
  }
}
