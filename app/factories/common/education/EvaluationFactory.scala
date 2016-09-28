package factories.common.education

import domain.common.education.Evaluation

/**
  * Created by SONY on 2016-09-28.
  */
class EvaluationFactory {
  def createEvaluation(values:Map[String,String]):Evaluation={

    Evaluation(evaluationId = values("evaluationId"),name = values("name"))
  }

}
