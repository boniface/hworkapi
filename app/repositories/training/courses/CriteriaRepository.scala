package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.Criteria

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class CriteriaRepository extends CassandraTable[CriteriaRepository, Criteria] {

  object criteriaId extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)



  override def fromRow(r: Row): Criteria = {
    Criteria(
      criteriaId(r),
      name(r),

    )
  }
}

object CriteriaRepository extends CriteriaRepository with RootConnector {
  override lazy val tableName = "coursecriteria"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course:Criteria): Future[ResultSet] = {
    insert
      .value(_.criteriaId, course.criteriaId)
      .value(_.name, course.name)
      .future()
  }

  def getAllCriteria: Future[Seq[Criteria]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCriteriaById(id: String): Future[Option[Criteria]] = {
    select.where(_.criteriaId eqs id).one()
  }


}
