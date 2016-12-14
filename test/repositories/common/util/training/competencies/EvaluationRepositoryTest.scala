package repositories.common.util.training.competencies

import conf.connection.DataConnection
import domain.training.competencies.Evaluation
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.competencies.EvaluationRepository
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class EvaluationRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    EvaluationRepository.create.ifNotExists().future()
  }
//  Evaluation(evaluationId:String, name:String)
  test("testSaveOrUpdate") {
    val evaluation = Evaluation(
      "eval1",
      "good"
    )

    val result = Await.result(EvaluationRepository.save(evaluation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetEvaluation") {
    val result = Await.result(EvaluationRepository.getEvaluationById("eval1"), 2.minutes)
    assert( result.head.name === "good")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    EvaluationRepository.truncate().future()
  }
}
