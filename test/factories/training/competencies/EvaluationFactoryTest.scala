package factories.training.competencies
import org.scalatest.FunSuite
import domain.training.competencies.Evaluation
import factories.training.competency.EvaluationFactory

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class EvaluationFactoryTest extends FunSuite {
  test("testCreateEvaluation")
  {
    val values = Map("evaluationId"->"1000", "name"->"Medium")

    val evaluation = EvaluationFactory.createEvaluation(values)
    assert(evaluation == Evaluation(evaluationId="1000", name="Medium"))
  }
}
