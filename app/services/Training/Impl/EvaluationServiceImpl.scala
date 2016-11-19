package services.Training.Impl

import domain.training.competencies.{Evaluation, Competency, CompetencyType}
import repositories.Training.competencies.{EvaluationRepository, CompetencyTypeRepository}
import services.Service
import services.Training.EvaluationService
import scala.concurrent.Future
import com.websudos.phantom.dsl._
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

  def getEvaluation(): Future[Seq[Evaluation]] = {
    EvaluationRepository.getAllEvaluation
  }


}