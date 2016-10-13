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

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): DegreeType = {
    DegreeType(id(r), name(r), state(r))
  }
}

object DegreeTypeRepository extends DegreeTypeRepository with RootConnector {
  override lazy val tableName = "degreetypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(degreeType: DegreeType): Future[ResultSet] = {
    insert
      .value(_.id, degreeType.id)
      .value(_.name, degreeType.name)
      .value(_.state, degreeType.state)
      .future()
  }

  def findById(id: String): Future[Option[DegreeType]] = {
    select.where(_.id eqs id).one()
  }

  def findAll: Future[Seq[DegreeType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
