package repositories.Training.competencies
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.competencies.CompetencyType

/**
 * Created by gavin.ackerman on 2016-11-08.
 */
class CompetencyTypeRepository extends CassandraTable[CompetencyTypeRepository, CompetencyType] {

  object competencyTypeId extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)



  override def fromRow(r: Row): CompetencyType = {
    CompetencyType(
      competencyTypeId(r),
      name(r),
    )
  }
}

object CompetencyTypeRepository extends CompetencyTypeRepository with RootConnector {
  override lazy val tableName = "competencytype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(comp: CompetencyType): Future[ResultSet] = {
    insert
      .value(_.competencyTypeId, comp.competencyTypeId)
      .value(_.name, comp.name)

      .future()
  }

  def getAllCompetencyType: Future[Seq[CompetencyType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCompetencyTypeById(id: String): Future[Option[CompetencyType]] = {
    select.where(_.competencyTypeId eqs id).one()
  }
}
