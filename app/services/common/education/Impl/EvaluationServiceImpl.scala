package services.common.education.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.education.Evaluation
import repositories.common.education.EvaluationRepository
import services.Service
import services.common.education.EvaluationService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class EvaluationServiceImpl extends EvaluationService with Service{

  override def createOrUpdate(evaluation: Evaluation): Future[ResultSet] = {
    EvaluationRepository.save(evaluation)
  }

  override def getEvaluationById(evaluationId: String): Future[Option[Evaluation]] = {
    EvaluationRepository.getEvaluationById(evaluationId)
  }

  override def getEvaluations(evaluationId: String): Future[Seq[Evaluation]] = {
    EvaluationRepository.getEvaluations(evaluationId)
  }
}
