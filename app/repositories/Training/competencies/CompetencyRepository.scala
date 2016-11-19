package repositories.Training.competencies
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.competencies.Competency

/**
 * Created by gavin.ackerman on 2016-11-08.
 */
class CompetencyRepository extends CassandraTable[CompetencyRepository, Competency] {

  object id extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)

  object competencyTypeId extends StringColumn(this)


  override def fromRow(r: Row): Competency = {
    Competency(
      id(r),
      name(r),
      competencyTypeId(r)
    )
  }
}

object CompetencyRepository extends CompetencyRepository with RootConnector {
  override lazy val tableName = "competency"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(comp: Competency): Future[ResultSet] = {
    insert
      .value(_.id, comp.compencyId)
      .value(_.name, comp.name)
      .value(_.competencyTypeId, comp.competencyTypeId)
      .future()
  }

  def getAllcomp: Future[Seq[Competency]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getcompById(id: String): Future[Option[Competency]] = {
    select.where(_.id eqs id).one()
  }
}
