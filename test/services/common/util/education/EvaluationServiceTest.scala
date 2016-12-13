package services.common.util.education

import domain.common.education.Evaluation
import org.scalatest.FunSuite
import services.common.education.EvaluationService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class EvaluationServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val evaluation = Evaluation(
      "EvaluationID",
      "Exam")

    val result = Await.result(EvaluationService.apply.createOrUpdate(evaluation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetEvaluation") {
    val result = Await.result(EvaluationService.apply.getEvaluationById("EvaluationID"), 2.minutes)
    assert( result.head.name === "Exam")
  }
}
