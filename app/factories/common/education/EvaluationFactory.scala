package factories.common.education

import domain.common.education.Evaluation

class EvaluationFactory {
  def createEvaluation(values:Map[String, String]):Evaluation={
 Evaluation(evaluationId = values("evaluationId"),name = values("name"))
  }

}