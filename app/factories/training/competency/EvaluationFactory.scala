package factories.training.competency

import domain.training.competencies.Evaluation


/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class EvaluationFactory {
  def createEvaluation(values:Map[String, String]):Evaluation={
    Evaluation(evaluationId = values("evaluationId"), name = values("name")))
  }
}