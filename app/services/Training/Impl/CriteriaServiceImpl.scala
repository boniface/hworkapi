package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.Criteria
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CriteriaRepository
import services.Service
import services.Training.{CriteriaService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CriteriaServiceImpl extends CriteriaService with Service{
  def createOrUpdate(criteria: Criteria): Future[ResultSet] = {
    CriteriaRepository.save(criteria)
  }

  def getCriteriaById( id: String): Future[Option[Criteria]] = {
    CriteriaRepository.getCriteriaById(id)
  }

  def getCriteria(): Future[Seq[Criteria]] = {
    CriteriaRepository.getAllCriteria
  }


}