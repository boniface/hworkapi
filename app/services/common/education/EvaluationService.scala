package services.common.education

import com.websudos.phantom.dsl._
import domain.common.education.Evaluation
import services.common.education.Impl.EvaluationServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait EvaluationService {

  def createOrUpdate(evaluation:Evaluation):Future[ResultSet]

  def getEvaluationById(evaluationId: String): Future[Option[Evaluation]]

  def getEvaluations(evaluationId: String): Future[Seq[Evaluation]]
}

object EvaluationService{
  def apply: EvaluationService = new EvaluationServiceImpl()

}
