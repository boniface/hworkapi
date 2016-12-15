package services.common.util.training.competencies

import domain.common.util.Mail
import domain.training.competencies.Evaluation
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.Training.EvaluationService
import services.common.util.MailService
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by gavin.ackerman on 2016-12-15.
 */
class EvaluationServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val evaluation = Evaluation(
      "eval1",
      "good"
    )

    val result = Await.result(EvaluationService.apply.createOrUpdate(evaluation), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetEvaluation") {
    val result = Await.result(EvaluationService.apply.getEvaluationById("eval1"), 2.minutes)
    assert( result.head.name === "good")
  }
}
