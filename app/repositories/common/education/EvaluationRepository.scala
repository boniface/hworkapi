package repositories.common.education

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.education.Evaluation

import scala.concurrent.Future

sealed class EvaluationRepository extends CassandraTable[EvaluationRepository,Evaluation]{
  object evaluationId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): Evaluation = {
    Evaluation(evaluationId(r),name(r))
  }
}

object EvaluationRepository extends EvaluationRepository with RootConnector {
  override lazy val tableName = "evaluation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(evaluation: Evaluation): Future[ResultSet] = {
    insert
      .value(_.evaluationId, evaluation.evaluationId)
      .value(_.name, evaluation.name)
      .future()
  }

  def findById(evaluationId: String):Future[Option[Evaluation]] = {
    select.where(_.evaluationId eqs evaluationId).one()
  }
  def findAll: Future[Seq[Evaluation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(evaluationId:String): Future[ResultSet] = {
    delete.where(_.evaluationId eqs evaluationId).future()
  }
}
