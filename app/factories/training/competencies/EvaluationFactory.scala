package factories.training.competencies

import domain.training.competencies.Evaluation

/**
  * Created by SONY on 2016-10-18.
  */
class EvaluationFactory
{
  def createEvaluation(values: Map[String, String]): Evaluation=
  {
    Evaluation(evaluationId = values("evaluationId"), name = values("name"))
  }

}
