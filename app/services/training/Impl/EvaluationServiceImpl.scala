package services.Training.Impl

import domain.training.competencies.{Evaluation}
import services.Service
import services.Training.EvaluationService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.competencies.EvaluationRepository
/**
 * Created by gavin.ackerman on 2016-11-12.
 */
class EvaluationServiceImpl extends EvaluationService with Service{
  def createOrUpdate(evaluation: Evaluation): Future[ResultSet] = {
    EvaluationRepository.save(evaluation)
  }

  def getEvaluationById( id: String): Future[Option[Evaluation]] = {
    EvaluationRepository.getEvaluationById( id)
  }

  def getEvaluations(id: String): Future[Seq[Evaluation]] = {
    EvaluationRepository.getEvaluation(id)
  }


}