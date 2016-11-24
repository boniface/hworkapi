package repositories.Training.competencies
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.competencies.Evaluation

/**
 * Created by gavin.ackerman on 2016-11-08.
 */
class EvaluationRepository extends CassandraTable[EvaluationRepository, Evaluation] {

  object evaluationId extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)



  override def fromRow(r: Row): Evaluation = {
    Evaluation(
      evaluationId(r),
      name(r)
    )
  }
}

object EvaluationRepository extends EvaluationRepository with RootConnector {
  override lazy val tableName = "evaluation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(eval: Evaluation): Future[ResultSet] = {
    insert
      .value(_.evaluationId, eval.evaluationId)
      .value(_.name, eval.name)

      .future()
  }

  def getAllEvaluation: Future[Seq[Evaluation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getEvaluationById(id: String): Future[Option[Evaluation]] = {
    select.where(_.evaluationId eqs id).one()
  }
}
